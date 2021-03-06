package com.yw.springbootdemo.model;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangwei
 * @date 2019/5/9 14:41
 */
@Data
public class CheckItem implements Serializable {
    private static final long serialVersionUID = -789317600095094518L;

    private String id;
    /**
     * 名称
     */
    private String name;

    private String superId;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 1可用，0不可用，-1删除
     */
    private Integer enable;

    /**
     * 是否检测项目 1是0不是
     */
    private Integer isItem;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 检验部门ID
     */
    private String departmentId;

    /**
     * 标准法规：多选
     */
    @TableField(condition = SqlCondition.LIKE)
    private String lawsStandardIds;

    /**
     * 报告编号
     */
    @TableField(condition = SqlCondition.LIKE)
    private String reportNo;

    /**
     * 是否生成报告：1是0否
     */
    private Integer reportOut;

    /**
     * 项目类型：字典多选
     */
    @TableField(condition = SqlCondition.LIKE)
    private String projectTypes;

    /**
     * 费用类别：字典
     */
    private String costType;

    /**
     * 费用标准
     */
    private BigDecimal costStandard;

    /**
     * 计费单位：字典
     */
    private String costUnit;

    /**
     * 工时
     */
    private BigDecimal costUseHour;

    /**
     * 计算公式：字典
     */
    private String costFormula;

    /**
     * 起价值
     */
    private BigDecimal costLadder;

    /**
     * 起步价
     */
    private BigDecimal costStart;

    /**
     * 质量系数：1采用0不采用
     */
    private Integer qualityFormat;

    /**
     * 占用场地：1占用0不占用
     */
    private Integer placeUse;

    /**
     * 对应场地
     */
    private String placeId;

    /**
     * 场地系数：1采用0不采用
     */
    private Integer placeFormat;

    /**
     * 产生劳务：1产生0不产生
     */
    private Integer laborUse;

    /**
     * 对应劳务
     */
    private String laborId;

    /**
     * 劳务类别：字典
     */
    private String laborType;

    /**
     * 设备使用工时
     */
    private BigDecimal deviceUseHour;

    /**
     * 仪器设备ids：多选
     */
    private String deviceIds;

    /**
     * 车辆类型：字典多选
     */
    private String vehicleTypes;

    /**
     * 是否关键项目：1是0否
     */
    private Integer isImportant;

    /**
     * 辅助条件
     */
    private String matchCondition;

    /**
     * 备注
     */
    private String remark;

    private Date createDate;

    //补助标准
    private BigDecimal subsidyStandard;

    //每班标准里程
    private BigDecimal mileageStandard;

    //补助计算公式
    private String subsidyFormula;
}
