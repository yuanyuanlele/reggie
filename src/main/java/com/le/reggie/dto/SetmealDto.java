package com.le.reggie.dto;


import com.le.reggie.entity.Setmeal;
import com.le.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
