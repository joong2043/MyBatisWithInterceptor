package com.ll.exam.app_2022_09_23.app.article.repostiory;

import com.ll.exam.app_2022_09_23.app.article.dto.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ArticleRepository {

    @Select("""
            <script>
            SELECT *
            FROM article
            </script>
            """)
    List<Article> getArticles();

    @Insert("""
            <script>
            insert into article
            set createDate=NOW(),
            modifyDate=NOW(),
            subject=#{subject},
            content=#{content}
            </script>
            """)
    void write(String subject, String content);

    @Select("""
            select LAST_INSERT_ID()
            """)
    long getLastInsertId();

    @Select("""
            <script>
            select * 
            from article
            where id = #{id}
            </script>
            """)
    Article getArticleById(long id);

    @Select("""
            <script>
            select A.*
            from article as A
            where 1
            <if test="kw != ''">
            and A.subject like concat('%',#{kw},'%')
            </if>
            </script>
            """)
    List<Article> search(String kwType, String kw);
}
