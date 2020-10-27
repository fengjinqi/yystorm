package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.UserDTO;
import com.yystorm.entity.Article;
import com.yystorm.entity.User;
import com.yystorm.mapper.UserMapper;
import com.yystorm.service.UserRoleService;
import com.yystorm.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystorm.vo.ArticleVO;
import com.yystorm.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public String getWxAccessToken(String appid, String secret, String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getWxUserInfo(String openid, String access_token) {
        return restTemplate.getForObject("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid, String.class);
    }


    @Override
    @Transactional
    public int register(User user) {
        int insert = baseMapper.insert(user);
        if (insert > 0) {
            userRoleService.createUserRole(user.getId(), 2);
        }
        return insert;
    }

    /**
     * 根据openid查询用户
     *
     * @param openid
     * @return
     */
    @Override
    public User getUserInfo(String openid) {


        return baseMapper.selectOne(new QueryWrapper<User>().eq("openid", openid));
    }

    @Override
    public int putUser(UserDTO userDTO, String id) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return baseMapper.updateById(user);
    }

    /**
     *  我的文章
     * @param page1
     * @param id
     * @return
     */
    @Override
    public IPage<ArticleVO> findAllArticle(Page<Article> page1, Integer id) {
        return baseMapper.findAllArticle(page1,id);

    }

    @Override
    public UserVo getUserInfos(String usernameFromToken) {

        return baseMapper.selectOnes(usernameFromToken);
    }


}
