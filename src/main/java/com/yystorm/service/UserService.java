package com.yystorm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.UserDTO;
import com.yystorm.entity.Article;
import com.yystorm.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yystorm.vo.ArticleVO;
import com.yystorm.vo.UserVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-10
 */
public interface UserService extends IService<User> {
    String getWxAccessToken(String appid, String secret, String code);

    String getWxUserInfo(String openid, String access_token);


    int register(User user);

    User getUserInfo(String openid);


    /**
     * 修改资料
     * @param userDTO
     * @param id
     * @return
     */
    int putUser(UserDTO userDTO, String id);

    /**
     * 我的文章
     * @param page1
     * @param id
     * @return
     */
    IPage<ArticleVO> findAllArticle(Page<Article> page1, Integer id);

    /**
     * 个人中心
     * @param usernameFromToken
     * @return
     */
    UserVo getUserInfos(String usernameFromToken);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    UserVo selectOneId(String id);
}
