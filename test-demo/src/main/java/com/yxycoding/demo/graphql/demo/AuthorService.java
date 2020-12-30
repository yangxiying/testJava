package com.yxycoding.demo.graphql.demo;

/*
 * 我们需要有一个服务进行处理Author，这里我们省去DAO层，直接从service中进行构造数据，实际项目中service在调用dao即可
 * @author yangxy
 * @date 2020/12/24 08:52
 */

import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    public Author findById(int id) {
        Author author = new Author();
        author.setId(id);
        if(id==1) {
            author.setName("悟纤");
            author.setPhoto("/img/1.png");
        }else if(id==2) {
            author.setName("悟空");
            author.setPhoto("/img/2.png");
        }
        return author;
    }
}
