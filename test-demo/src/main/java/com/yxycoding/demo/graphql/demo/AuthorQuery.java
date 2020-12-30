package com.yxycoding.demo.graphql.demo;
/*
 *
 * @author yangxy
 * @date 2020/12/24 08:53
 */

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorQuery implements GraphQLQueryResolver {
    @Autowired
    private AuthorService authorService;


    public Author findAuthorById(int id) {
        return authorService.findById(id);
    }
}
