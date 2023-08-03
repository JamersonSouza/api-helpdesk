package com.api.helpdesk.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.helpdesk.domain.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "https://helpdesk-frontend-pvpgiti6w-jamersonsouza.vercel.app")
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


     public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
             AuthenticationManager authenticationManager2, JWTUtil jwtUtil) {
         super(authenticationManager);
         authenticationManager = authenticationManager2;
         this.jwtUtil = jwtUtil;
     }

    //método de tentativa de autenticação
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

       try {
        CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);

        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), 
        creds.getSenha(), new ArrayList<>());

        Authentication auth = authenticationManager.authenticate(userToken);

        return auth;
       } catch (Exception e) {
        throw new RuntimeException(e);
       }

    }

    //método de sucesso na autenticação
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        String username =  ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.setHeader("access-control-expose-headers", "Authorization");
        response.setHeader("Authorization", "Bearer " + token);
    }

    //método de não sucesso na autenticação
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
                
                response.setStatus(401);
                response.setContentType("application/json");
                response.getWriter().append(json());
        
    }


    private CharSequence json() {
        long date = new Date().getTime();
        return "{"
                     + "\"timestamp\": " + date + ", " 
                     + "\"status\": 401, "
                     + "\"error\": \"Não autorizado\", "
                     + "\"message\": \"Email ou senha inválidos\", "
                     + "\"path\": \"/login\"}";
                }


}
