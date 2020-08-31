package com.flitsneak.goods.service.impl;

import com.changgou.goods.pojo.Brand;
import com.flitsneak.goods.dao.BrandMapper;
import com.flitsneak.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    /**
     * 全部数据
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加品牌
     * @param brand
     */
    @Override
    public void addBrand(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 修改品牌信息
     * @param brand
     */
    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }
    /**
     * delete brand by id
     * @param id
     */
    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * search brand by multi-term
     * @param brand
     * @return
     */

    @Override
    public List<Brand> findList(Brand brand) {
        Example example = createExample(brand);

        List<Brand> brands = brandMapper.selectByExample(example);

        return brands;
    }

    private Example createExample(Brand brand){
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if (brand !=null){
            if(!StringUtils.isEmpty(brand.getName())){
                criteria.andLike("name","%" + brand.getName() + "%");
            }
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter",brand.getLetter());

            }
        }
        return example;
    }

    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {

        //1.开始分页 紧跟着的[第一个查询 才会被分页]
        PageHelper.startPage(page, size);
        //2.执行查询
       List<Brand> brands = brandMapper.selectAll();
       return new PageInfo<>(brands);
    }
    /**
     * 根据条件分页查询
     * @param brand
     * @return
     */
    @Override
    public PageInfo<Brand> page(Integer page, Integer size, Brand brand) {
        PageHelper.startPage(page,size);
        Example example= createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        return new PageInfo<>(brands);
    }
}
