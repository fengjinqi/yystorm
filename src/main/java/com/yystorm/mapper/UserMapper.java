package com.yystorm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.entity.Article;
import com.yystorm.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystorm.vo.ArticleVO;
import com.yystorm.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-10
 */
public interface UserMapper extends BaseMapper<User> {
    List findAll();

    IPage<ArticleVO> findAllArticle(Page<Article> page,@Param("id") Integer id);

    @Select("select u.*,(select count(id) from user_follows where user_follows.user_id=u.id) as follow_count,(select count(id) from user_follows where user_follows.followed_user=u.id) as fans_count from user as u where openid=#{openid}")
    UserVo selectOnes(String openid);
}
