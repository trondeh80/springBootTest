package com.bouvet.springtest;
import org.neo4j.driver.v1.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class RecipeController {

    private String uri = "bolt://localhost:7687";
    private String user = "neo4j";
    private String password = "password";
    private Driver driver;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/recipes")
    @ResponseBody
    ArrayList<Map> listRecipes(){
        return getRecipes();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RecipeController.class, args);
    }

    private  ArrayList<Map> getRecipes(){
        try (Session session = getDriver().session()){
            StatementResult sr = session.run("MATCH (n:Recipe) RETURN n.id as id, n.name as name LIMIT 28");
            ArrayList<Map> list = new ArrayList<Map>();
            while (sr.hasNext()){
                Record r = sr.next();
                list.add(r.asMap());
            }
            return list;
        }
    }

    private Driver getDriver(){
        if (driver == null) {
            driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        }
        return driver;
    }

}
