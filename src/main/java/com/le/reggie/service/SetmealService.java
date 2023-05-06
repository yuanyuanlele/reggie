package com.le.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.le.reggie.dto.SetmealDto;
import com.le.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);

    /**
     * 查询套餐，同时需要查询套餐和菜品的关联数据
     * @param id
     */
    public SetmealDto getByIdWithDish(long id);

    /**
     * 修改套餐，同时需要修改套餐和菜品的关联数据
     * @param setmealDto
     */
    public void updateWithDish(SetmealDto setmealDto);

    /**
     * 修改启售停售
     * @param status
     * @param ids
     */
    public void updateStatus(int status, List<Long> ids);
}
