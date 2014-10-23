package com.jcwx.game.common.constants;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;
import org.apache.log4j.Logger;

import com.jcwx.game.common.ResourceBundleService;
import com.jcwx.game.web.OperationLogAnnotation;

public class OperationLogConstant {
    
    /** 获取，新增类型 */
    public static final int TYPE_GET = 1;
    /** 普通类型 */
    public static final int TYPE_NORMAL = 0;
    /** 消耗，减少类型 */
    public static final int TYPE_USE = 2;
    /****** 2013-12-25 添加 *****/

    /** 成就奖励 */
    @OperationLogAnnotation(label = "成就奖励", type = TYPE_USE)
    public static final String ACHIEVE_AWARD = "achieve_award";
    /** 活动 恶魔入侵副本奖励 */
    @OperationLogAnnotation(label = "活动 恶魔入侵副本奖励", type = TYPE_USE)
    public static final String ACTIVITY_DEMON_COPY = "activityDemonCopy";
    /** 活动 恶魔入侵副本 重置 */
    @OperationLogAnnotation(label = "节日活动副本重置", type = TYPE_USE)
    public static final String ACTIVITY_DEMON_REST = "activityDemonRest";

    /** 活动 恶魔入侵副本 兑换称号 */
    @OperationLogAnnotation(label = "活动 恶魔入侵副本 兑换称号", type = TYPE_USE)
    public static final String ACTIVITY_DEMON_TRADE_HONOUR = "activityDemonTradeHonour";

    /** 活动奖励 */
    @OperationLogAnnotation(label = "活动奖励", type = TYPE_GET)
    public static final String ACTIVITY_REWARD = "activityReward";

    /**
     * 增加军团资金
     * */
    @OperationLogAnnotation(label = "增加军团资金", type = TYPE_USE)
    public static final String ADD_ARMY_GOLD = "addArmyGold";

    /** 后台添加 **/
    @OperationLogAnnotation(label = "后台添加 礼券", type = TYPE_GET)
    public static final String ADD_BIND_GOLD = "addBindGold";
    /** 后台添加魔晶 **/
    @OperationLogAnnotation(label = "后台添加魔晶", type = TYPE_GET)
    public static final String ADD_GOLD = "addGold";
    /**
     * 增加金币
     * */
    @OperationLogAnnotation(label = "增加金币", type = TYPE_USE)
    public static final String ADD_SILVER = "addSilver";
    /** 建筑物捐赠 **/
    @OperationLogAnnotation(label = "建筑物捐赠 ", type = TYPE_USE)
    public static final String ARMY_ACTIVITY_CONTRIBUTE = "armyActivityContribute ";

    /** 军团 建筑物 **/
    @OperationLogAnnotation(label = "军团 建筑物", type = TYPE_USE)
    public static final String ARMY_ACTIVITY_DEVELOP = "armyActivityDevelop";
    /** 军团捐赠 **/
    @OperationLogAnnotation(label = "军团捐赠", type = TYPE_USE)
    public static final String ARMY_CONTRIBUTE = "armyContribute ";
    /** 军团 捐献 **/
    @OperationLogAnnotation(label = "军团 捐献", type = TYPE_USE)
    public static final String ARMY_DEVELOP = "armyDevelop";

    /** 军团祭拜神恩 **/
    @OperationLogAnnotation(label = "军团祭拜神恩", type = TYPE_USE)
    public static final String ARMY_GOD = "armyGod";

    /** 军团神树施肥 **/
    @OperationLogAnnotation(label = "军团神树施肥", type = TYPE_USE)
    public static final String ARMY_GOD_TREE = "armyGodTree";

    /** 军团-怪物入侵副本活动 **/
    @OperationLogAnnotation(label = "军团-怪物入侵副本活动", type = TYPE_USE)
    public static final String ARMY_INVASION_COPY = "armyInvasionCopy";

    /** 军团 邮件提取 **/
    @OperationLogAnnotation(label = "军团 邮件提取", type = TYPE_USE)
    public static final String ARMY_MESSAGE = "armyMessage";

    /** 军团改名 **/
    @OperationLogAnnotation(label = "军团改名", type = TYPE_USE)
    public static final String ARMY_RENAME = "armyRename";
    /** 军团神树施肥奖励 **/
    @OperationLogAnnotation(label = "军团神树施肥奖励", type = TYPE_USE)
    public static final String ARMY_TREE_REWORD = "armyTreeReword";

    /** 军团福利 */
    @OperationLogAnnotation(label = "军团福利", type = TYPE_GET)
    public static final String ARMY_WELFARE = "armyWelfare";

    /** 军团 神像祭拜 **/
    @OperationLogAnnotation(label = "军团  神像祭拜", type = TYPE_USE)
    public static final String ARMY_WORSHIP = "armyWorship";

    /** 属性培养 **/
    @OperationLogAnnotation(label = "属性培养", type = TYPE_USE)
    public static final String ATTRIBUTE_TRAIN = "attributeTrain";

    /** 竞价宣战 被超越 **/
    @OperationLogAnnotation(label = "竞价宣战 被超越", type = TYPE_USE)
    public static final String AUCTION_BE_PASSED = "auctionBePassed";

    /**** 拍卖托管费 ******/
    @OperationLogAnnotation(label = "拍卖托管费", type = TYPE_USE)
    public static final String AUCTION_FEE = "auctionFee";
    /**************** 军团资金变化情况 *****************************************/
    /** 竞价宣战成功 **/
    @OperationLogAnnotation(label = "竞价宣战成功", type = TYPE_USE)
    public static final String AUCTION_SUCCESS = "auctionSuccess";
    @OperationLogAnnotation(label = "装备打造消耗", type = TYPE_USE)
    /** 装备打造消耗 */
    public static final String BUILDEQUIP = "build_equip";
    /** 商城购买 **/
    @OperationLogAnnotation(label = "活动商城购买", type = TYPE_USE)
    public static final String BUY_ACTIVITY_MALL = "buyActivityMall";
    /** 购买符文背包 **/
    @OperationLogAnnotation(label = "购买符文背包", type = TYPE_USE)
    public static final String BUY_AMULET_BAG = "buyAmuletBag";
    /**************** 竞价买下物品 ******************/
    @OperationLogAnnotation(label = "竞价买下物品", type = TYPE_USE)
    public static final String BUY_AUCTION_FEE = "buyAuctionFee";
    /** 购买装备 **/
    @OperationLogAnnotation(label = "购买装备", type = TYPE_USE)
    public static final String BUY_EQUIP = "buyEquip";
    /********** 拍卖行一口价 *****************/
    @OperationLogAnnotation(label = "拍卖行一口价", type = TYPE_USE)
    public static final String BUY_FROM_AUCTION = "buyFromAuction";
    /** 购买制作水饺 **/
    @OperationLogAnnotation(label = "购买制作水饺", type = TYPE_USE)
    public static final String BUY_JIAO = "buyJiao";
    /** 商城购买 **/
    @OperationLogAnnotation(label = "商城购买 ", type = TYPE_USE)
    public static final String BUY_MALL = "buyMall";
    /*************** 一口价买下东西花费 ********************/
    @OperationLogAnnotation(label = "一口价买下东西花费", type = TYPE_USE)
    public static final String BUY_MAX_AUCTION_FEE = "buyMaxAuctionFee";

    /****** 多人副本购买次数 ********/
    @OperationLogAnnotation(label = "多人副本购买次数  ", type = TYPE_USE)
    public static final String BUY_MUILTY_COPY_TIMES = "buyMuiltyCopyTimes";

    /** 购买VIP **/
    /*
     * @OperationLogAnnotation(label = "购买VIP", type = TYPE_USE) public static
     * final String BUY_VIP = "buyVip";
     */
    /** 购买体力 **/
    @OperationLogAnnotation(label = "购买体力", type = TYPE_USE)
    public static final String BUY_POWER = "buyPower";

    /** 购买体力增加体力 **/
    @OperationLogAnnotation(label = "购买体力增加体力", type = TYPE_GET)
    public static final String BUY_POWER_ADD = "buyPowerAdd";
    /** ======================== 金币 操作常量 ======================== **/
    /** 购买道具 **/
    @OperationLogAnnotation(label = "购买道具", type = TYPE_USE)
    public static final String BUY_PROPERTY = "buyProperty";
    /******* 合成宝石直接购买宝石 *********/
    @OperationLogAnnotation(label = "合成宝石直接购买宝石", type = TYPE_USE)
    public static final String BUY_STONE_FOR_MIXTURE = "buyStoneForMixture";

    /** 回购遗失经验 **/
    @OperationLogAnnotation(label = "回购遗失经验 ", type = TYPE_USE)
    public static final String BUYBACK_LOSE_EXP = "buybackLoseExp";

    /** 宝石转换 */
    @OperationLogAnnotation(label = "宝石转换", type = TYPE_USE)
    public static final String CHANGE_STONE = "changeStone";

    /** 充值魔晶 **/
    @OperationLogAnnotation(label = "充值魔晶", type = TYPE_GET)
    public static final String CHARGE_GOLD = "chargeGold";

    /**** 百宝箱兑换 *****/
    @OperationLogAnnotation(label = "百宝箱兑换", type = TYPE_USE)
    public static final String CIMELIA_BOX_EXCHANGE = "cimeliaBoxExchange";

    /***************** 城战 竞价宣战 ****************/
    @OperationLogAnnotation(label = "城战 竞价宣战", type = TYPE_USE)
    public static final String CITY_WAR_AUCTION = "cityWarAuction";
    /** 城战复活 **/
    @OperationLogAnnotation(label = "城战复活", type = TYPE_USE)
    public static final String CITY_WAR_RECOVER = "cityWarRecover";
    /** 城战 胜利 **/
    @OperationLogAnnotation(label = "城战 胜利", type = TYPE_USE)
    public static final String CITY_WAR_WINNER = "cityWarWinner";

    /** 矿山清除Cd **/
    @OperationLogAnnotation(label = "矿山清除Cd", type = TYPE_USE)
    public static final String CLEAN_MINE_CD = "cleanMineCD";

    /** 巅峰对决清除Cd **/
    @OperationLogAnnotation(label = "巅峰对决清除Cd", type = TYPE_USE)
    public static final String CLEAN_RING_CD = "cleanRingCD";

    /** 恶梦之渊扫荡消耗 **/
    @OperationLogAnnotation(label = "恶梦之渊扫荡消耗", type = TYPE_USE)
    public static final String CLEAR_DREAM_COST = "clearDreamCost";

    /** 宝石合成 **/
    @OperationLogAnnotation(label = "宝石合成", type = TYPE_USE)
    public static final String COMBIN_STONE = "combineStone";

    /** 完成功能加礼券 **/
    @OperationLogAnnotation(label = "完成功能加礼券  ", type = TYPE_GET)
    public static final String COMPLETE_FUNCTIOM = "completeFunction";

    /** 副本扫荡 **/
    @OperationLogAnnotation(label = "副本扫荡清除CD", type = TYPE_USE)
    public static final String COPY_CLEAR = "copyClear";

    /** 副本掉落 */
    @OperationLogAnnotation(label = "副本掉落", type = TYPE_GET)
    public static final String COPY_FALL = "copyFall";

    /** 副本复活 **/
    @OperationLogAnnotation(label = "副本复活", type = TYPE_USE)
    public static final String COPY_RECOVER = "copyRecover";

    /** 副本奖励 **/
    @OperationLogAnnotation(label = "副本奖励", type = TYPE_GET)
    public static final String COPY_REWARD = "copyReward";

    /*** 噩梦之渊 *****/
    @OperationLogAnnotation(label = "噩梦之渊", type = TYPE_USE)
    public static final String COPYTYPEDR = "COPYTYPEDR";

    /*** 秘境寻宝 *****/
    @OperationLogAnnotation(label = "秘境寻宝", type = TYPE_USE)
    public static final String COPYTYPEMJXB = "COPYTYPEMJXB";

    /** 创建军团 **/
    @OperationLogAnnotation(label = "创建军团", type = TYPE_USE)
    public static final String CREATE_ARMY = "createArmy";

    /*** 日常任务奖励 ***/
    @OperationLogAnnotation(label = "日常任务奖励", type = TYPE_GET)
    public static final String DAILY_TASK_REWARD = "dailyTaskReward";

    /** 日常任务升星 **/
    @OperationLogAnnotation(label = "日常任务升星", type = TYPE_USE)
    public static final String DAILY_TASK_UPGRADE_STAR = "dailyTaskUpgradeStar";

    /****** 分解装备 ********/
    @OperationLogAnnotation(label = "分解装备", type = TYPE_USE)
    public static final String DECOMPOSE_EQUIP = "decomposeEquip";
    @OperationLogAnnotation(label = "增加挑战心魔次数", type = TYPE_USE)
    /*****增加挑战心魔次数****/
    public static final String DEVIL_ADD_COUNT = "devilAddCount";

    /*** 拍卖出价---不是消耗，只是记录拍卖出价多少 ********/
    @OperationLogAnnotation(label = "记录拍卖出价", type = TYPE_USE)
    public static final String DO_AUCTION_FEE = "doAuctionFee";

    /******* 一口价出价---不是消耗，只是记录拍卖出价多少 *****************/
    @OperationLogAnnotation(label = "记录拍卖一口价出价", type = TYPE_USE)
    public static final String DO_MAX_AUCTION_FEE = "doMaxAuctionFee";

    /** 提取附件获得 */
    @OperationLogAnnotation(label = "提取附件获得", type = TYPE_GET)
    public static final String DRAW_ACCE = "drawAcce";
    /****** 时装购买 ********/
    @OperationLogAnnotation(label = "时装购买", type = TYPE_USE)
    public static final String DRESS_BUY = "dressbuy";

    @OperationLogAnnotation(label = "时装进阶", type = TYPE_USE)
    /******时装进阶********/
    public static final String DRESS_JINGJIE = "dressjinjie";

    /** 时装通灵 **/
    @OperationLogAnnotation(label = "时装通灵", type = TYPE_USE)
    public static final String DRESS_TONGLING = "dressTongling";

    /****** 时装培养 ********/
    @OperationLogAnnotation(label = "时装培养", type = TYPE_USE)
    public static final String DRESS_TRAIN = "dressTrain";

    /** 装备合成 **/
    @OperationLogAnnotation(label = "装备合成", type = TYPE_USE)
    public static final String EQUIP_COM = "equipCom";
    /**** 远征深渊挂机扫荡 ******/
    @OperationLogAnnotation(label = "远征深渊挂机扫荡", type = TYPE_GET)
    public static final String EXPEDITOIN = "expedition";
    /** 抽取邮件中附件:来源系统发送 **/
    @OperationLogAnnotation(label = "抽取邮件中附件:来源系统发送", type = TYPE_USE)
    public static final String EXTRACT_ATTACHMENT_SYSTEM = "extractAttachmentSystem";
    /** 逆天改运 **/
    @OperationLogAnnotation(label = "逆天改运", type = TYPE_USE)
    public static final String FATE_TO_LUCK = "fateToLuck";
    /********** 一键完成日常任务 *****************/
    @OperationLogAnnotation(label = "一键完成日常任务", type = TYPE_USE)
    public static final String FINISH_DAILY_TASK = "finishDailyTask";
    /** 首冲获得 */
    @OperationLogAnnotation(label = "*首冲获得", type = TYPE_GET)
    public static final String FIRST_RECHARGE = "firstRecharge";
    /**** 继承装备 ****/
    @OperationLogAnnotation(label = "继承装备", type = TYPE_USE)
    public static final String FOLLOW_EQUIP = "followEquip";
    @OperationLogAnnotation(label = "灵药园", type = TYPE_USE)
    /** 灵药园 */
    public static final String GARDEN = "garden";
    @OperationLogAnnotation(label = "灵药园购买种子", type = TYPE_USE)
    /** 灵药园购买种子 */
    public static final String GARDEN_BUY = "garden_buy";
    @OperationLogAnnotation(label = "灵药园冷却花费金币", type = TYPE_USE)
    /** 灵药园冷却花费金币 */
    public static final String GARDEN_COOLING = "garden_cooling";
    @OperationLogAnnotation(label = "菜园刷新种子品质消耗魔晶", type = TYPE_USE)
    /** 菜园刷新种子品质消耗魔晶 */
    public static final String GARDEN_REFRESH = "gardenRefresh";
    /** 撰写符文 **/
    @OperationLogAnnotation(label = "撰写符文", type = TYPE_USE)
    public static final String GENERAL_AMULET = "generalAmulet";
    /***************** 获取箱子奖励消耗魔金 *******************************************/
    @OperationLogAnnotation(label = "副本一键开启所有箱子", type = TYPE_USE)
    public static final String GET_BOX_REWARD = "getBoxReward";
    /** 回购物品获得 **/
    @OperationLogAnnotation(label = "回购物品获得", type = TYPE_GET)
    public static final String GET_ITEM = "getItem";
    /**
     * 橙装
     * */
    @OperationLogAnnotation(label = "获得橙装", type = TYPE_GET)
    public static final String GET_ORANGE_EQUIP = "getorangeEquip";
    /**
     * 紫装
     * */
    @OperationLogAnnotation(label = "获得紫装", type = TYPE_GET)
    public static final String GET_PURPLE_EQUIP = "getpurpleEquip";
    /** GM-OSS添加礼券 **/
    @OperationLogAnnotation(label = "GM-OSS添加礼券 ", type = TYPE_GET)
    public static final String GM_ADD_BIND_GOLD = "gmAddBindGold";
    /** GM-OSS添加魔晶 **/
    @OperationLogAnnotation(label = "GM-OSS添加魔晶 ", type = TYPE_GET)
    public static final String GM_ADD_GOLD = "gmAddGold";
    /** GM-OSS添加金币 **/
    @OperationLogAnnotation(label = "GM-OSS添加金币  券 ", type = TYPE_GET)
    public static final String GM_ADD_SILVER = "gmAddSilver";
    /** 炼金 **/
    @OperationLogAnnotation(label = "炼金", type = TYPE_USE)
    public static final String GOLD_METALLURY = "goldMetallurgy";
    /** 小助手 */
    @OperationLogAnnotation(label = "小助手", type = TYPE_GET)
    public static final String HELP_REWARD = "helpReward";
    @OperationLogAnnotation(label = "铭刻精炼", type = TYPE_USE)
    public static final String INSCRIBE_JL = "inscribejl";
    /** 邀请符文大师 **/
    @OperationLogAnnotation(label = "邀请符文大师 ", type = TYPE_USE)
    public static final String INVITE_AMULET_MASTER = "inviteAmuletMaster";

    /** 水饺活动 **/
    @OperationLogAnnotation(label = "水饺活动 ", type = TYPE_USE)
    public static final String JIAO = "jiao";

    /** 水饺兑换称号 **/
    @OperationLogAnnotation(label = "水饺兑换称号", type = TYPE_USE)
    public static final String JIAO_TITLE = "jiaoTitle";

    /** 水饺祝福 **/
    @OperationLogAnnotation(label = "水饺祝福", type = TYPE_USE)
    public static final String JIAO_ZHUFU = "jiaoZhufu";

    /** 积分兑换物品 **/
    @OperationLogAnnotation(label = "积分兑换物品 ", type = TYPE_USE)
    public static final String JIFEN_EXCHANGE = "jifenExchange";

    public static final int LOG_BATCH_FIVE = 5; // 5分钟日志调度策略

    public static final int LOG_BATCH_ONE = 1; // 1分钟日志调度策略

    public static final int LOG_BATCH_SIZE = 20; // 批量插入日志的条数

    public static final int LOG_INSERT_SIZE = 100000; // 日志插入时缓存最低日志条数

    private static Logger logger = Logger.getLogger(OperationLogConstant.class);

    public static Map<String, String> maptype = new LinkedHashMap<String, String>();

    /**
     * 提取附件
     * */
    @OperationLogAnnotation(label = "邮件提取", type = TYPE_GET)
    public static final String MESSAGE_EXTRACTATTACHMENT = "extractAttachment";

    /** 炼金获得 **/
    @OperationLogAnnotation(label = "炼金获得", type = TYPE_GET)
    public static final String METALLURY = "metallurgy";
    /** 寻宝购买次数 */
    @OperationLogAnnotation(label = "秘境寻宝购买次数", type = TYPE_USE)
    public static final String MJXB_BUY = "mjxbbuy";

    /****
     * 精英副本消耗体力******、/
     * 
     */
    @OperationLogAnnotation(label = "精英副本消耗体力", type = TYPE_USE)
    public static final String MUIL_COPY_COST_POWER = "muilCopyCostPower";

    /**
     * 多人副本产出
     * */
    @OperationLogAnnotation(label = "多人副本产出 ", type = TYPE_GET)
    public static final String MUTI_COPY_CREATE = "mutiCopyCreate";

    /** 道具消耗 */
    @OperationLogAnnotation(label = "道具消耗", type = TYPE_USE)
    public static final String NORMAL = "normal";

    /** ======================== 魔晶-礼券 操作常量 ======================== **/
    /** 在线奖励获得 */
    @OperationLogAnnotation(label = "在线奖励获得", type = TYPE_GET)
    public static final String ONLINE_REWARD = "onlineReward";
    /** 背包开锁 **/
    @OperationLogAnnotation(label = "背包开锁 ", type = TYPE_USE)
    public static final String OPEN_BAG_LOCK = "openBagLock";
    /**** 开启百宝箱 ****/
    @OperationLogAnnotation(label = "开启百宝箱", type = TYPE_USE)
    public static final String OPEN_CIMELIA_BOX = "openCimeliaBox";
    /** 装备开孔 **/
    @OperationLogAnnotation(label = "装备开孔", type = TYPE_USE)
    public static final String OPEN_EQUIP_HOLE = "openEquipHole";
    /** 仓库开锁 **/
    @OperationLogAnnotation(label = "仓库开锁 ", type = TYPE_USE)
    public static final String OPEN_WARE_LOCK = "openWareLock";
    /** 充值加魔晶 */
    @OperationLogAnnotation(label = "充值加魔晶", type = TYPE_USE)
    public static final String PAY_GOLD = "payGold";

    /********** 拍卖行竞价 *****************/
    @OperationLogAnnotation(label = "拍卖行竞价", type = TYPE_USE)
    public static final String PLAYER_AUCTION = "playerAuction";
    /** 属性培养 **/
    @OperationLogAnnotation(label = "属性培养", type = TYPE_USE)
    public static final String PLAYER_TRAIN = "playerTrain";
    /** 许愿树 */
    @OperationLogAnnotation(label = "许愿树", type = TYPE_GET)
    public static final String PRAY_TREE = "prayTree";
    /** 许愿树清除Cd **/
    @OperationLogAnnotation(label = "许愿树清除Cd", type = TYPE_USE)
    public static final String PRAY_TREE_CD = "prayTreeCd";

    /***************** 2013-11-22 新加类型 *********************/

    /**************** 刷新日常任务 ********************************/
    @OperationLogAnnotation(label = "刷新日常任务", type = TYPE_USE)
    public static final String REFRESH_DAILY_TASK_COST = "refreshDailyTaskCost";

    /** 摘除宝石 **/
    @OperationLogAnnotation(label = "摘除宝石", type = TYPE_USE)
    public static final String REMOVE_EQUIP_STONE = "removeEquipStone";
    /******** 重置试炼之地 **********/
    @OperationLogAnnotation(label = "重置试炼之地*  ", type = TYPE_USE)
    public static final String RESET_TRIALS_LAND = "resetTrialsLand";
    /** 购买 巅峰对决 **/
    @OperationLogAnnotation(label = "购买 巅峰对决", type = TYPE_USE)
    public static final String RING_BUY = "ringBuy";

    /** 巅峰对决奖励 **/
    @OperationLogAnnotation(label = "巅峰对决奖励", type = TYPE_USE)
    public static final String RING_REWARD = "ringReward";

    /** 寻找矿石 **/
    @OperationLogAnnotation(label = "寻找矿石", type = TYPE_USE)
    public static final String SEARCH_MINE = "searchMine";

    /********* 神秘商店购买物品 *******/
    @OperationLogAnnotation(label = "神秘商店购买物品", type = TYPE_USE)
    public static final String SECRET_SHOP_BUY = "secretShopBuy";

    /** 出售物品 **/
    @OperationLogAnnotation(label = "出售物品", type = TYPE_GET)
    public static final String SELL_ITEM = "sellItem";

    /** 发送军团邮件 **/
    @OperationLogAnnotation(label = "发送军团邮件", type = TYPE_USE)
    public static final String SEND_ARMY_EMAIL = "sendArmyEmail";

    /** 发送邮件 **/
    @OperationLogAnnotation(label = "发送邮件", type = TYPE_USE)
    public static final String SEND_MAIL = "sendMail";
    /** 签到奖励 */
    @OperationLogAnnotation(label = "签到奖励", type = TYPE_GET)
    public static final String SIGN_REWARD = "signReward";

    /** 技能洗点 **/
    @OperationLogAnnotation(label = "技能洗点", type = TYPE_USE)
    public static final String SKILL_CLEAN = "skillClean";

    /** 出售碎片 **/
    @OperationLogAnnotation(label = "出售碎片", type = TYPE_USE)
    public static final String SOLD_OUT_AMULET = "soldOutAmulet";

    @OperationLogAnnotation(label = "星宿升级消耗", type = TYPE_USE)
    /**星宿升级消耗*/
    public static final String STAR_UPGRADE = "starUpgrade";

    /***** 强化装备 ******/
    @OperationLogAnnotation(label = "强化装备", type = TYPE_USE)
    public static final String STRENGTH_EQUIP = "strengthEquip";

    /** 系统邮件 */
    @OperationLogAnnotation(label = "系统邮件", type = TYPE_USE)
    public static final String SYS_MAIL = "sys_mail";
    /*** 任务奖励 ***/
    @OperationLogAnnotation(label = "任务奖励", type = TYPE_GET)
    public static final String TASK_ADD_SILVER = "taskAddSilver";
    /** 任务奖励 */
    @OperationLogAnnotation(label = "任务奖励", type = TYPE_GET)
    public static final String TASK_REWARD = "taskReward";

    /** 洗练继承 **/
    @OperationLogAnnotation(label = "总计 ", type = TYPE_USE)
    public static final String TOTLE = "total";
    /**** 试炼之地挂机扫荡 ******/
    @OperationLogAnnotation(label = "试炼之地挂机扫荡", type = TYPE_GET)
    public static final String TRIALS_LAND = "trialsLand";

    /** 进入试炼之地消耗活力 **/
    @OperationLogAnnotation(label = "进入试炼之地消耗活力", type = TYPE_USE)
    public static final String TRIALSLAND_CONSUME_POWER = "trialslandConsumePower";

   

    /**
     * 消耗军团资金
     * */
    @OperationLogAnnotation(label = "消耗军团资金", type = TYPE_USE)
    public static final String USE_ARMY_GOLD = "useArmyGold";

    /**
     * 消费礼券
     * */
    @OperationLogAnnotation(label = "消费礼券", type = TYPE_USE)
    public static final String USE_BIND_GOLD = "useBindGold";

    /** 消费魔晶 **/
    @OperationLogAnnotation(label = "消费魔晶", type = TYPE_USE)
    public static final String USE_GOLD = "useGold";

    /**
     * 橙装
     * */
    @OperationLogAnnotation(label = "销毁橙装", type = TYPE_USE)
    public static final String USE_ORANGE_EQUIP = "useorangeEquip";

    /**************** 使用道具消耗 *****************************************/
    @OperationLogAnnotation(label = "使用道具消耗", type = TYPE_USE)
    public static final String USE_PROP_COST_GOLD = "usePropCostGold";

    /**************** 使用道具消耗活力 *****************************************/
    @OperationLogAnnotation(label = "使用道具消耗活力", type = TYPE_USE)
    public static final String USE_PROP_COST_POWER = "usePropCostPower";
    /**************** 使用道具消耗 *****************************************/
    @OperationLogAnnotation(label = "使用道具消耗", type = TYPE_USE)
    public static final String USE_PROP_COST_SILVER = "usePropCostSilver";

    /** 使用道具 **/
    @OperationLogAnnotation(label = "使用道具", type = TYPE_GET)
    public static final String USE_PROPERTY = "useProperty";

    /**
     * 紫装
     * */
    @OperationLogAnnotation(label = "销毁紫装", type = TYPE_USE)
    public static final String USE_PURPLE_EQUIP = "usepurpleEquip";

    /**
     * 消费金币
     * */
    @OperationLogAnnotation(label = "消费金币", type = TYPE_USE)
    public static final String USE_SILVER = "useSilver";

    /****** 洗练消耗金币数量 ********/
    @OperationLogAnnotation(label = "洗练消耗金币数量", type = TYPE_USE)
    public static final String WASH_COST_SILVER = "washCostSilver";

    /** 洗练 **/
    @OperationLogAnnotation(label = "洗练", type = TYPE_USE)
    public static final String WASH_EQUIP = "washEquip";

    /** 洗练消耗洗练石 **/
    @OperationLogAnnotation(label = "洗练消耗洗练石", type = TYPE_USE)
    public static final String WASH_EQUIP_USE_STONE = "washEquipUseStone";

    /** 洗练继承 **/
    @OperationLogAnnotation(label = "洗练继承 ", type = TYPE_USE)
    public static final String WASH_FOLLOW_EQUIP = "washFollowEquip";

    /** 世界boss鼓舞 **/
    @OperationLogAnnotation(label = "世界boss鼓舞", type = TYPE_USE)
    public static final String WORLD_BOSS_INSPIRE = "worldBossInspire";
    /** 世界boss复活 **/
    @OperationLogAnnotation(label = "世界boss复活", type = TYPE_USE)
    public static final String WORLD_BOSS_RECOVER = "worldBossRecover";
    /** 砸蛋活动 **/
    @OperationLogAnnotation(label = "砸蛋活动", type = TYPE_USE)
    public static final String YOKA = "yoka";
    /** 砸蛋兑换称号 **/
    @OperationLogAnnotation(label = "砸蛋兑换称号", type = TYPE_USE)
    public static final String YOKA_TITLE = "yokaTitle";

    static {
	try {
	    initTypeArray();
	} catch (Exception e) {
	    logger.error(ResourceBundleService.getString("txt.exception"), e);
	}
    }

    public static String getTypeLabel(String key) {
	return maptype.get(key);
    }

    public static String getTypeLabell() {
	return maptype.get("buyVip");
    }

    /**
     * 加载class时进行反射处理
     * 
     * @throws Exception
     */
    public static void initTypeArray() throws Exception {
	maptype.put("queryAll", "查询全部");
	Class<?> c = OperationLogConstant.class;
	Field[] fs = c.getDeclaredFields();
	for (int i = 0; i < fs.length; i++) {
	    Field f = fs[i];
	    OperationLogAnnotation opan = f
		    .getAnnotation(OperationLogAnnotation.class);
	    if (opan != null) {
		String label = opan.label();
		String value = (String) f.get(null);
		maptype.put(value, label);
	    }

	}
    }

}
