package com.yw.springbootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssi.common.utils.ExcelImportUtil;
import com.yw.springbootdemo.mapper.CheckItemMapper;
import com.yw.springbootdemo.model.CheckItem;
import com.yw.springbootdemo.service.ICheckItemService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yangwei
 * @date 2019/5/9 14:58
 */
@Service
public class CheckItemService extends ServiceImpl<CheckItemMapper, CheckItem>
        implements ICheckItemService {

    @Override
    public List<Map<String, Object>> check() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<CheckItem> dbList = list();
        List<CheckItem> excelList = getExcelData();
        Map<String, CheckItem> map = excelList.stream().collect(Collectors.toMap(CheckItem::getId, checkItem -> checkItem));
        dbList.forEach(dbCheckItem -> {
            String id = dbCheckItem.getId();
            CheckItem excelCheckItem = map.get(id);
            if (excelCheckItem != null) {
                map.remove(id);
            }
            List<String> diff = getDifferent(dbCheckItem, excelCheckItem);
            if (CollectionUtils.isNotEmpty(diff)) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put(id, diff);
                list.add(map1);
            }
        });
        System.out.println(list.size() + "~~~~~~~~~~");
        return list;
    }

    private List<String> getDifferent(CheckItem c1, CheckItem c2) {
        List<String> list = new ArrayList<>();
        if (c2 == null) {
            list.add("no excelCheckItem");
            return list;
        }
        if (!c1.getName().equals(c2.getName())) {
            list.add("项目名称 数据库：" + c1.getName() + " " + "excel：" +c2.getName());
        }
        if (!c1.getReportNo().equals(c2.getReportNo())) {
            list.add("报告编号 数据库：" + c1.getReportNo() + " " + "excel：" +c2.getReportNo());
        }
        if (c1.getReportOut() != c2.getReportOut()) {
            list.add("是否生成报告号 数据库：" + c1.getReportOut() + " " + "excel：" +c2.getReportOut());
        }
        if (!c1.getProjectTypes().equals(c2.getProjectTypes())) {
            list.add("项目类型 数据库：" + c1.getProjectTypes() + " " + "excel：" +c2.getProjectTypes());
        }
        if (!c1.getCostType().equals(c2.getCostType())) {
            list.add("检验费用类别 数据库：" + c1.getCostType() + " " + "excel：" +c2.getCostType());
        }
        //11 费用标准
        if (!c1.getCostUnit().equals(c2.getCostUnit())) {
            list.add("计费单位 数据库：" + c1.getCostUnit() + " " + "excel：" +c2.getCostUnit());
        }
        // 13检验标准工时
        if (!c1.getCostFormula().equals(c2.getCostFormula())) {
            list.add("计算公式 数据库：" + c1.getCostFormula() + " " + "excel：" +c2.getCostFormula());
        }
        //15 起价值
        //16 起步价
        if (c1.getQualityFormat() != null &&
                c1.getQualityFormat() != c2.getQualityFormat()) {
            list.add("质量系数 数据库：" + c1.getQualityFormat() + " " + "excel：" +c2.getQualityFormat());
        }
        if (c1.getPlaceUse() != null &&
                c1.getPlaceUse() != c2.getPlaceUse()) {
            list.add("占用场地 数据库：" + c1.getPlaceUse() + " " + "excel：" +c2.getPlaceUse());
        }
        if (!c1.getPlaceId().equals(c2.getPlaceId())) {
            list.add("对应场地 数据库：" + c1.getPlaceId() + " " + "excel：" +c2.getPlaceId());
        }
        if (c1.getPlaceFormat() != null &&
                c1.getPlaceFormat() != c2.getPlaceFormat()) {
            list.add("场地系数 数据库：" + c1.getPlaceFormat() + " " + "excel：" +c2.getPlaceFormat());
        }
        if (c1.getLaborUse() != null &&
                c1.getLaborUse() != c2.getLaborUse()) {
            list.add("产生劳务 数据库：" + c1.getLaborUse() + " " + "excel：" +c2.getLaborUse());
        }
        if (!c1.getLaborId().equals(c2.getLaborId())) {
            list.add("对应劳务 数据库：" + c1.getLaborId() + " " + "excel：" +c2.getLaborId());
        }
        if (!c1.getLaborType().equals(c2.getLaborType())) {
            list.add("劳务类别 数据库：" + c1.getLaborType() + " " + "excel：" +c2.getLaborType());
        }
        //24 设备使用工时
        if (!c1.getDeviceIds().equals(c2.getDeviceIds())) {
            list.add("仪器设备 数据库：" + c1.getDeviceIds() + " " + "excel：" +c2.getDeviceIds());
        }
        if (c1.getIsImportant() != c2.getIsImportant()) {
            System.out.println(c1.getId());
            list.add("是否关键项目 数据库：" + c1.getIsImportant() + " " + "excel：" +c2.getIsImportant());
        }
        if (!c1.getVehicleTypes().equals(c2.getVehicleTypes())) {
            list.add("车辆样品类型 数据库：" + c1.getVehicleTypes() + " " + "excel：" +c2.getVehicleTypes());
        }
        if (!c1.getMatchCondition().equals(c2.getMatchCondition())) {
            list.add("项目辅助条件 数据库：" + c1.getMatchCondition() + " " + "excel：" +c2.getMatchCondition());
        }
        if (!c1.getRemark().equals(c2.getRemark())) {
            list.add("备注 数据库：" + c1.getRemark() + " " + "excel：" +c2.getRemark());
        }
        //32
        //33
//        if (!c1.getSubsidyFormula().equals(c2.getSubsidyFormula())) {
//            list.add("补助计算公式 数据库：" + c1.getSubsidyFormula() + " " + "excel：" +c2.getSubsidyFormula());
//        }
        return list;
    }

    private List<CheckItem> getExcelData() {
        List<CheckItem> list = new ArrayList<>();
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:checkItem.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Object[]> data = ExcelImportUtil.importExcel(file.getPath());
        data.forEach(item -> {
            CheckItem checkItem = new CheckItem();
            String name = (String) item[0];
            checkItem.setName(name.replaceAll(" ",""));
            checkItem.setId((String) item[1]);
            String status = (String) item[6]; //项目状态
            if ("启用".equals(status)) {
                checkItem.setEnable(1);
            } else if ("停用".equals(status)) {
                checkItem.setEnable(0);
            } else {
                checkItem.setEnable(-1);
            }
            checkItem.setReportNo((String) item[7]); //报告编号
            checkItem.setReportOut("是".equals(item[8]) ? 1 : 0); //是否生成报告号
            checkItem.setProjectTypes((String) item[9]); //项目类型
            checkItem.setCostType((String) item[10]); //检验费用类别
            checkItem.setCostStandard(change(item[11])); //费用标准
            checkItem.setCostUnit((String) item[12]); //计费单位
            checkItem.setCostUseHour(change(item[13])); //检验标准工时
            checkItem.setCostFormula((String) item[14]); //计算公式
            checkItem.setCostLadder(change(item[15])); //起价值
            checkItem.setCostStart(change(item[16])); //起步价
            checkItem.setQualityFormat("采用".equals(item[17]) ? 1 : 0); //质量系数
            checkItem.setPlaceUse("占用".equals(item[18]) ? 1 : 0); //占用场地
            checkItem.setPlaceId((String) item[19]); //对应场地
            checkItem.setPlaceFormat("采用".equals(item[20]) ? 1 : 0); //场地系数
            checkItem.setLaborUse("产生".equals(item[21]) ? 1 : 0); //产生劳务
            checkItem.setLaborId((String) item[22]); //对应劳务
            checkItem.setLaborType((String) item[23]); //劳务类别
            checkItem.setDeviceUseHour(change(item[24])); //设备使用工时
            checkItem.setDeviceIds((String) item[25]); //仪器设备
            checkItem.setIsImportant("是".equals(item[26]) ? 1 : 0); //是否关键项目
            checkItem.setVehicleTypes((String) item[27]); //车辆样品类型
            checkItem.setMatchCondition((String) item[28]); //项目辅助条件
            checkItem.setRemark((String) item[29]); //备注
            if (item.length == 35) {
                checkItem.setSubsidyStandard(change(item[32])); //补助标准
                checkItem.setMileageStandard(change(item[33])); //每班标准里程
                checkItem.setSubsidyFormula((String) item[34]); //补助计算公式
            }
            list.add(checkItem);
        });
        return list;
    }

    private BigDecimal change (Object o) {
        if (o == null || o == "") {
            return null;
        }
        return new BigDecimal((String) o);
    }
}
