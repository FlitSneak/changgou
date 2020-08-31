package com.flitsneak.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.flitsneak.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {
    @Autowired
    private BrandService brandService;
    /**
     * 查询所有数据
     * @return
     */
    @GetMapping
    public Result<Brand> findAll(){
        List<Brand> list = brandService.findAll();
        return new Result<Brand>(true, StatusCode.OK,"查询成功",list);
    }
    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id){
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true,StatusCode.OK,"查询成功",brand);

    }
    /**
     * 增加品牌
     * @param brand
     * @return
     */
    @PostMapping
    public Result addBrand(@RequestBody Brand brand){
        brandService.addBrand(brand);
        return new Result(true,StatusCode.OK,"增加品牌成功");
    }
    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    @PutMapping("/{id}")
    public Result updateBrand(@RequestBody Brand brand,@PathVariable Integer id){
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result(true,StatusCode.OK,"修改品牌成功");
    }
    /**
     * delete brand by id
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result deleteBrand(@PathVariable Integer id){
        brandService.deleteBrand(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    /**
     * search brand by multi-term
     * @param brand
     * @return
     */
    @PostMapping("/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand){
        List<Brand> brands = brandService.findList(brand);
        return new Result<>(true,StatusCode.OK,"搜索成功",brands);

    }

    /**
     *  分页查询
     * @param page 当前页
     * @param size 每页显示的行
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value="page")Integer page,@PathVariable(value="size") Integer size){
        PageInfo<Brand> info = brandService.findPage(page,size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询成功",info);
    }
    /**
     * 根据前端传过来的搜索条件分页查询
     * @param page
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> page(@PathVariable Integer page,@PathVariable Integer size,@RequestBody Brand brand ){
        PageInfo<Brand> pageInfo = brandService.page(page, size, brand);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询成功",pageInfo);

    }

}
