package com.jcwx.game.common;

/**
 * 游戏配置信息
 * 
 * @author derek
 * @version 1.0
 */
public class GameConfig {

    /** 市民日常消耗的食物数量 */
    public static final int CITIZEN_CONSUME_FOOD = 1;

    /** 招募市民消耗的食物 */
    public static final int CITIZEN_COST_FOOD = 10;

    /** 招募市民花费的时间 */
    public static final int CITIZEN_COST_TIME = 5;

    /** 单位时间的粮食产量等级加成(百分比) */
    public static final int FOOD_OUTPUT_LEVEL_ADD = 2;

    /** 一个工人单位时间的粮食产量 */
    public static final int FOOD_OUTPUT_PER_WORKER = 30;

    /** 食物科技加成(百分比) */
    public static final int FOOD_TECH_ADD = 2;

    /** 新手保护期，单位天 */
    public static final int FRESHMAN_PROTECT_DAY = 7;

    /** 在税率为1的情况下单位时间每个闲人的金钱产量 */
    public static final double MONEY_OUTPUT_PER_FREEMAN = 0.05;

    /** 在税率为1的情况下单位时间每个工人的金钱产量 */
    public static final double MONEY_OUTPUT_PER_WORKER = 0.025;
    /** 单位时间的石油产量等级加成(百分比) */
    public static final int OIL_OUTPUT_LEVEL_ADD = 2;
    /** 一个工人单位时间的石油产量 */
    public static final int OIL_OUTPUT_PER_WORKER = 10;
    /** 石油科技加成(百分比) */
    public static final int OIL_TECH_ADD = 2;
    /** 报告过期的天数 */
    public static final int REPORT_OVERDUE_DAY = 14;

    /** 新兵日常消耗的食物数量 */
    public static final int SOLDIER_CONSUME_FOOD = 1;

    /** 新兵日常消耗的金钱数量 */
    public static final int SOLDIER_CONSUME_MONEY = 1;

    /** 招募新兵消耗的粮食 */
    public static final int SOLDIER_COST_FOOD = 20;
    /** 招募新兵消耗的金钱 */
    public static final int SOLDIER_COST_MONEY = 10;
    /** 单位时间的钢铁产量等级加成(百分比) */
    public static final int STEEL_OUTPUT_LEVEL_ADD = 2;
    /** 一个工人单位时间的钢铁产量 */
    public static final int STEEL_OUTPUT_PER_WORKER = 10;

    /** 钢铁科技加成(百分比) */
    public static final int STEEL_TECH_ADD = 2;
    /** 仓储科技加成(百分比) */
    public static final int STORAGE_TECH_ADD = 2;
    /** 超级住宅扩展时间，单位小时 */
    public static final int SUPERHOUSE_COST_TIME = 8;

    /** 单位时间的木材产量等级加成(百分比) */
    public static final int WOOD_OUTPUT_LEVEL_ADD = 2;

    /** 一个工人单位时间的木材产量 */
    public static final int WOOD_OUTPUT_PER_WORKER = 10;

    /** 木材科技加成(百分比) */
    public static final int WOOD_TECH_ADD = 2;

}
