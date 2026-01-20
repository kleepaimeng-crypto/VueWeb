package com.neuro;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

public class JwtTest {

    @Test
    public void testGenerateJwt(){

        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("username","admin");
        claims.put("id",1);

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256,"bmV1cm8K")//密钥
                .addClaims(claims)//添加自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))//设置过期时间
                .compact() ;//生成jwt
        System.out.println(jwt);

    }

    @Test
    public void testParseJwt(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2NDMzMjQ2NH0.4D9VUHhIohlw3wrcaLW6dc1GzpF8dKMdAChOOudZieg";
        Claims claims = Jwts.parser().setSigningKey("bmV1cm8K")//密钥
                .parseClaimsJws(jwt)//解析
                .getBody() ;//获取内容
        System.out.println(claims);

    }
}
