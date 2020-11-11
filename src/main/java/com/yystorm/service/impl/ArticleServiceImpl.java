package com.yystorm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystorm.dto.ArticleDTO;
import com.yystorm.dto.ArticleIsSHowDTO;
import com.yystorm.email.IMailService;
import com.yystorm.entity.Article;
import com.yystorm.entity.Messages;
import com.yystorm.mapper.ArticleMapper;
import com.yystorm.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystorm.service.MessagesService;
import com.yystorm.utils.MessageEnum;
import com.yystorm.vo.ArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengjinqi
 * @since 2020-10-16
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private MessagesService messagesService;
    @Autowired
    private IMailService iMailService;
    /**
     * 创建文章
     * @param articleDTO
     * @return
     */
    @Override
    @Transactional
    public Integer createArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        iMailService.sendSimpleMail("网站文章审核","有用户发布了一篇《"+article.getTitle()+"》文章，快去审核吧！","fengjinqi@fengjinqi.com","928227826@qq.com");
        return baseMapper.insert(article);
    }



    /**
     * 分类查询文章
     *
     * @param page
     * @param categoryId
     * @return
     */
    @Override
    public IPage<ArticleVO> getCategoryListMapper(Page<Article> page, String categoryId) {
        return  baseMapper.getCategoryListMapper(page, categoryId);
    }

    /**
     * 热门排行
     *
     * @return
     */
    @Override
    @Cacheable(value = "HotArticle",key = "'selectIndexList'")
    public List<ArticleVO> HotArticle() {
        List<Article> list = baseMapper.selectList(new QueryWrapper<Article>()
                .eq("is_show", "1").orderByDesc("click_nums").last("limit 10"));
        List<ArticleVO> articleDTOList = new ArrayList<>();
        for (Article article : list) {
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(article, articleVO);
            articleDTOList.add(articleVO);
        }
        return articleDTOList;
    }


    /**
     * 文章详情页
     *
     * @param id
     * @return
     */
    @Override
    public ArticleVO getArticleDetaile(String id) {
        baseMapper.updateDetaileClickNums(id);
        return baseMapper.selectOneById(id);
    }

    /**
     * 修改文章
     *
     * @param articleDTO
     * @return
     */
    @Override
    public Integer updateArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        return baseMapper.updateById(article);
    }

    /**
     * 随机
     * @param id
     * @return
     */
    @Override
    public List<Article> randomArticle(String id) {
        return baseMapper.randomArticle(id);
    }

    /**
     * 搜索
     * @param page1
     * @param name
     * @return
     */
    @Override
    public IPage<ArticleVO> getSearchList(Page<Article> page1, String name) {
        return  baseMapper.getSearchList(page1, name);
    }

    /**
     * 文章列表
     * @param page1
     * @param name
     * @param categoryId
     * @return
     */
    @Override
    public IPage<ArticleVO> getAdminListMapper(Page<Article> page1, String name, String categoryId) {
        return baseMapper.getAdminListMapper(page1,name,categoryId);
    }

    /**
     * 审核
     * @param articleIsSHowDTO
     * @return
     */
    @Override
    @Transactional
    public int updateByIdIsShow(ArticleIsSHowDTO articleIsSHowDTO) {
        Article article = new Article();
        article.setId(articleIsSHowDTO.getId());
        article.setIsShow(true);
        Messages messages = new Messages();
        messages.setContent(String.format("你投稿的文章%s审核通过","<span>《"+articleIsSHowDTO.getTitle()+"》</span>"));
        messages.setTitle(MessageEnum.ARTICLEENUM);
        messages.setContentId(articleIsSHowDTO.getId());
        messages.setAcceptId(articleIsSHowDTO.getUserId());

        int i = baseMapper.updateById(article);
        if (i>0){
            messagesService.createMessages(messages);
        }
        return i;
    }


}
