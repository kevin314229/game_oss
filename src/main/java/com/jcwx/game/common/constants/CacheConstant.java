package com.jcwx.game.common.constants;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jcwx.game.common.ResourceBundleService;
import com.jcwx.game.web.CommonAnnotation;

/**
 * 玩家缓存
 * 
 * @author Administrator
 * 
 */
public class CacheConstant {

    /*********** 主界面活动缓存 *********************/
    @CommonAnnotation(label = "主界面活动缓存")
    public static final String ACTION_PROP = "actionProp";

    /** 活动公告 **/
    @CommonAnnotation(label = "活动公告")
    public static final String ACTIVITY_NOTICE = "activityNotice";

    /** 活动map */
    @CommonAnnotation(label = "活动map")
    public static final String ACTIVITY_PROPERTY_MAP = "activityPropertyMap";

    /** 加自己为好友的玩家列表 * */
    @CommonAnnotation(label = "加自己为好友的玩家列表")
    public static final String ADD_MYFRIEND_LIST = "addMyFriendList";

    /**** 根据ip 限制访问 ********/
    @CommonAnnotation(label = "根据ip 限制访问")
    public static final String ADMIN_OPEN_IP_LIST = "adminOpenIpList";

    /** 所有NPC **/
    @CommonAnnotation(label = "所有NPC")
    public static final String ALL_NPC_MAP = "allNpc";

    /**** 竞技场分组缓存 **********/
    @CommonAnnotation(label = "竞技场分组缓存")
    public static final String ARENA_GROUP_MAP = "arenaGroupMap";

    /**** 竞技场正在匹配玩家缓存 ****/
    @CommonAnnotation(label = "竞技场正在匹配玩家缓存")
    public static final String ARENA_MATCH_MAP = "arenaMatchMap";

    /**** 竞技场对手实力匹配规则 *****************/
    @CommonAnnotation(label = "竞技场对手实力匹配规则")
    public static final String ARENA_MATCH_RULE_MAP = "arenaMatchRuleMap";

    /**** 竞技场战力阀值规则 *****************/
    @CommonAnnotation(label = "竞技场战力阀值规则")
    public static final String ARENA_MATCH_THRESHOLDS_MAP = "arenaMatchThresholdsMap";

    /***** 竞技场排行榜缓存 ****************/
    @CommonAnnotation(label = "竞技场排行榜缓存")
    public static final String ARENA_RANGE_MAP = "arenaRangeMap";
    /**** 竞技场活动军衔积分属性加成规则 *******/
    @CommonAnnotation(label = "竞技场活动军衔积分属性加成规则")
    public static final String ARENA_RANKINTEGRALATTRS_MAP = "arenaRankIntegralAttrsMap";

    // /***********玩家buff缓存*********************/
    // @CommonAnnotation(label="") public static final String PLAYER_BUFF =
    // "playerBuff";
    /************ 竞技场奖励缓存 ************************/
    @CommonAnnotation(label = "竞技场奖励缓存")
    public static final String ARENA_REWARD = "arenaReward";

    /**** 竞技场房间缓存 ***/
    @CommonAnnotation(label = "竞技场房间缓存")
    public static final String ARENA_ROOM_MAP = "arenaRoomMap";

    /** 竞技场商店物品 * */
    @CommonAnnotation(label = "竞技场商店物品")
    public static final String ARENA_STORE_MAP = "arenaStoreMap";

    /**** 竞技场连胜排名 ********/
    @CommonAnnotation(label = "竞技场连胜排名")
    public static final String ARENA_STREAK_RANKING = "arenaStreakRanking";

    /**** 竞技场连胜排名奖励 ********/
    @CommonAnnotation(label = "竞技场连胜排名奖励")
    public static final String ARENA_STREAK_RANKING_REWARD = "arenaStreakRankingReward";
    /**** 竞技场活动连胜获取积分规则 **********/
    @CommonAnnotation(label = "竞技场活动连胜获取积分规则")
    public static final String ARENA_WININTEGRALS_MAP = "arenaWinIntegralsMap";
    /** 军团事件缓存 * */
    @CommonAnnotation(label = "军团事件缓存 ")
    public static final String ARMY_EVENT_MAP = "armyEventMap";
    /** 军团神树列表 * */
    @CommonAnnotation(label = "军团神树列表")
    public static final String ARMY_GOD_TREE = "armyGodTree";
    /** 军团神树信息 */
    @CommonAnnotation(label = "军团神树信息")
    public static final String ARMY_GOD_TREE_PROPERTY = "armyGodTreeProperty";

    /** 获取军团贡献排名信息 */
    @CommonAnnotation(label = "获取军团贡献排名信息")
    public static final String ARMY_PLAYER_CONTRIBUTE_RANKING = "playerArmyContribute";

    /** 军团人物缓存 * */
    @CommonAnnotation(label = "军团人物缓存")
    public static final String ARMY_PLAYER_MAP = "armyPlayerMap";

    /** 军团神恩配置信息 */
    @CommonAnnotation(label = "军团神恩配置信息")
    public static final String ARMY_PROPERTY = "armyGodProperty";

    /** 自动开格子时间配置 */
    @CommonAnnotation(label = "自动开格子时间配置")
    public static final String BACK_PACK_TIME = "backPackTime";

    /** 军团信息缓存 * */
    @CommonAnnotation(label = "军团信息缓存")
    public static final String BASE_ARMY_MAP = "baseArmyMap";

    /** 人物基础属性 **/
    @CommonAnnotation(label = "人物基础属性 ")
    public static final String BASE_ATTRIBUTE = "baseAttribute";
    /** 基础装备缓存 * */
    @CommonAnnotation(label = "基础装备缓存 ")
    public static final String BASE_EQUIP_MAP = "baseEquipMap";
    /** 基础装备属性 **/
    @CommonAnnotation(label = "基础装备属性")
    public static final String BASE_EQUIP_PROP_MAP = "baseEquipPropMap";

    /** 基础地图信息缓存 **/
    @CommonAnnotation(label = "基础地图信息缓存 ")
    public static final String BASE_MAP_DATA = "baseMapData";
    /********** 强化所需金钱的基础值缓存 ************/
    @CommonAnnotation(label = "强化所需金钱的基础值缓存")
    public static final String BASE_MONEY_MAP = "baseMoneyMap";

    // 基础怪物
    @CommonAnnotation(label = "基础怪物 ")
    public static final String BASE_MONSTER_MAP = "baseMonsterMap";

    /** 基础道具缓存 * */
    @CommonAnnotation(label = "基础道具缓存")
    public static final String BASE_PROPERTY_MAP = "basePropertyMap";

    /** 基础技能缓存 * */
    @CommonAnnotation(label = "基础技能缓存")
    public static final String BASE_SKILL_MAP = "baseSkillMap";

    /** 基础任务缓存 * */
    @CommonAnnotation(label = "基础任务缓存")
    public static final String BASE_TASK_MAP = "baseTaskMap";

    /*********** BUFF配置文件 *********************/
    @CommonAnnotation(label = "BUFF配置文件")
    public static final String BUFF_PRO = "buffPro";

    /** 购买体力花费配置 */
    @CommonAnnotation(label = "购买体力花费配置")
    public static final String BUY_POWER = "buyPower";

    /**** 通用配置 试炼之地 ********/
    @CommonAnnotation(label = "通用配置  试炼之地")
    public static final String C_TRIALSLAND = "cTrialsLand";

    /**
     * 星尘召唤
     * */
    @CommonAnnotation(label = "星尘召唤")
    public static final String CALL_STAR = "callStar";

    /**** 通用配置 ********/
    @CommonAnnotation(label = "通用配置")
    public static final String COMMON_CONFIG = "commonConfig";

    /**** 宝石合成缓存 *********/
    @CommonAnnotation(label = "宝石合成缓存")
    public static final String CONBINE_STONE_MAP = "conbineStoneMap";

    /** 星座配置信息 */
    @CommonAnnotation(label = "星座配置信息")
    public static final String CONSTELLATION_PROPERTY = "constellation";

    /** 副本地图信息 **/
    @CommonAnnotation(label = "副本地图信息")
    public static final String COPY_MAP_DATA = "copyMapData";

    /**** 多人副本购买消耗魔金缓存 **********/
    @CommonAnnotation(label = "多人副本购买消耗魔金缓存")
    public static final String COST_COPY_GOLD = "costCopyGold";
    /**** 试炼之地购买消耗魔金缓存 **********/
    @CommonAnnotation(label = "试炼之地购买消耗魔金缓存")
    public static final String COST_TRIALS_GOLD = "costTrialsGold";
    /******** 日常任务奖励缓存 ***********/
    @CommonAnnotation(label = "日常任务奖励缓存")
    public static final String DAILY_TASK_REWARD_MAP = "dailyTaskRewardMap";

    /** 掉落对象缓存 **/
    @CommonAnnotation(label = "掉落对象缓存")
    public static final String DROP_ITEM_MAP = "dropItemMap";

    /*********** 装备分解，继承消耗金币缓存 *********************/
    @CommonAnnotation(label = "装备分解，继承消耗金币缓存")
    public static final String EQUIP_FACTOR = "equipFactor";

    /** 特殊掉落对象缓存 **/
    @CommonAnnotation(label = "特殊掉落对象缓存")
    public static final String EXCEPTION_DROP_MAP = "exceptionDropMap";

    /** 远征深渊map缓存 **/
    @CommonAnnotation(label = "远征深渊map缓存")
    public static final String EXPEDITION_CHASM_MAP = "expeditionChasmMap";

    /** 垃圾词汇过滤器 **/
    @CommonAnnotation(label = "垃圾词汇过滤器 ")
    public static final String FILTERWORDS = "filterWords";

    /**
     * 首冲
     * */
    @CommonAnnotation(label = "首冲")
    public static final String FIRST_PAY = "firstPay";

    /** 副本缓存 **/
    @CommonAnnotation(label = " 副本缓存 ")
    public static final String GAME_COPY = "gameCopy";

    // 副本区域对象
    @CommonAnnotation(label = "副本区域对象 ")
    public static final String GAME_COPY_AREA_MAP = "gameCopyAreaMap";

    //
    @CommonAnnotation(label = "副本对象 ")
    public static final String GAME_COPY_ID_MAP = "gameCopyIdMap";

    /** 副本怪物缓存 **/
    @CommonAnnotation(label = "副本怪物缓存  ")
    public static final String GAME_COPY_MONSTER = "gameCopyMonster";

    /** 怪物战斗数据 **/
    @CommonAnnotation(label = "怪物战斗数据")
    public static final String GAME_MONSTER_DATA = "gameMonsterData";

    /** 挂机系统配置缓存 * */
    @CommonAnnotation(label = "挂机系统配置缓存")
    public static final String GUAJI_MAP = "guaji";
    /** 挂机系统vip不同等级对应挂机时间配置 * */
    @CommonAnnotation(label = "挂机系统vip不同等级对应挂机时间配置")
    public static final String GUAJI_VIP_MAP = "guajiVip";

    /** 小助手奖励配置 */
    @CommonAnnotation(label = "小助手奖励配置 ")
    public static final String HELPER_REWARD = "helperReward";
    private static Logger logger = Logger.getLogger(CacheConstant.class);
    /** 已注册登录名缓存 **/
    @CommonAnnotation(label = " 已注册登录名缓存 ")
    public static final String LOGIN_NAME_CACHE = "loginNameMap";

    @CommonAnnotation(label = "邮件")
    public static final String MALL = "mall";

    /** 多人副本大厅map */
    @CommonAnnotation(label = "多人副本大厅map")
    public static final String MANY_COPY_MAP_PROPERTY = "manyCopyPropertyMap";

    /** 多人副本大厅map */
    @CommonAnnotation(label = "多人副本大厅map")
    public static final String MANY_COPY_ROOM = "manyCopyRoom";

    /** 地图路点信息缓存 **/
    @CommonAnnotation(label = "地图路点信息缓存 ")
    public static final String MAP_ROAD_MAP = "mapRoadMap";

    public static Map<String, String> maptype = new LinkedHashMap<String, String>();

    /** 炼金缓存 */
    @CommonAnnotation(label = "炼金缓存")
    public static final String METALLURGY_GOLD = "MetallurgYGold";
    /** 矿山map **/
    @CommonAnnotation(label = "矿山map")
    public static final String MINE_MAP = "mineMap";

    /** 矿山配置文件map **/
    @CommonAnnotation(label = "矿山配置文件map")
    public static final String MINE_PROPERTY_MAP = "minePropertyMap";

    /***
     * 充值
     * */
    @CommonAnnotation(label = "充值")
    public static final String MONEY_RECHARGE = "recharge";

    // 怪物AI
    @CommonAnnotation(label = "怪物AI ")
    public static final String MONSTER_AI = "monsterAi";

    /** 怪物类型属性加成 **/
    @CommonAnnotation(label = "怪物类型属性加成")
    public static final String MONSTER_CUSTOMIZE = "monsterCustomize";

    /** 多人副本对象缓存 **/
    @CommonAnnotation(label = "多人副本对象缓存 ")
    public static final String MULTI_GAME_COPY = "multiGameCopy";

    /** 生成名字缓存字库 */
    @CommonAnnotation(label = "生成名字缓存字库")
    public static final String NAME_LIB_MAP = "nameLibMap";

    /** 分地图NPC缓存 * */
    @CommonAnnotation(label = "分地图NPC缓存")
    public static final String NPC_MAP = "npc";

    /**** 离线玩家装备缓存 **********/
    @CommonAnnotation(label = "离线玩家装备缓存")
    public static final String OFFLINE_PLAYER_CACHE = "offLinePlayerCache";

    /**
     * 在线挂机名单map
     */
    @CommonAnnotation(label = "在线挂机名单map")
    public static final String ONLINE_GUAJI_MAP = "onlineGuaji";

    /**
     * 在线奖励
     * */
    @CommonAnnotation(label = "在线奖励")
    public static final String ONLINE_REWARD = "onlineReward";
    /** 操作日志缓存 **/
    @CommonAnnotation(label = "操作日志缓存")
    public static final String OPERATION_LOG = "operationLog";

    /** 账号在线缓存(多个角色的) */
    @CommonAnnotation(label = "账号在线缓存(多个角色的) ")
    public static final String PALYER_CACHE = "playerCache";
    /**
     * 平台兑换缓存 Key 代表 平台#Id
     * */
    @CommonAnnotation(label = "平台兑换缓存 ")
    public static final String PAY_PLAT = "payPlat";
    /** PK副本对象缓存 **/
    @CommonAnnotation(label = "PK副本对象缓存 ")
    public static final String PK_GAME_COPY = "pkGameCopy";
    /** 切磋副本对象缓存 **/
    @CommonAnnotation(label = "切磋副本对象缓存 ")
    public static final String PK_PLAY_COPY = "pkPlayCopy";
    /** 巅峰对决副本对象缓存 **/
    @CommonAnnotation(label = "巅峰对决副本对象缓存 ")
    public static final String PK_RING_COPY = "pkRingCopy";
    /** 军团申请记录缓存 * */
    @CommonAnnotation(label = "军团申请记录缓存")
    public static final String PLAYER_APPLYARMY_MAP = "playerApplyArmyMap";
    /** 军团被禁言用户列表 * */
    @CommonAnnotation(label = "军团被禁言用户列表")
    public static final String PLAYER_ARMY_BANNED = "playerArmyBanned";
    /** 军团神恩列表 * */
    @CommonAnnotation(label = "军团神恩列表")
    public static final String PLAYER_ARMY_GOD = "playerArmyGod";
    /** 玩家属性加成cache **/
    @CommonAnnotation(label = "玩家属性加成cache")
    public static final String PLAYER_ATTRIBUTE_MULTIPLE = "playerAttributeMultiple";
    /** 角色信息缓存 * */
    @CommonAnnotation(label = "角色信息缓存")
    public static final String PLAYER_BASE_MAP_NAME = "playBaseMapByName";
    /** 角色好友信息缓存 * */
    @CommonAnnotation(label = "角色好友信息缓存")
    public static final String PLAYER_FRIEND_BASE_MAP = "playerFriendBaseMap";
    /** 角色黑名单缓存 * */
    @CommonAnnotation(label = "角色黑名单缓存")
    public static final String PLAYER_FRIEND_BLACK_LIST = "playerFriendBlackList";
    /** 角色好友缓存 * */
    @CommonAnnotation(label = "角色好友缓存")
    public static final String PLAYER_FRIEND_LIST = "playerFriendList";
    /** 角色离线消息 * */
    @CommonAnnotation(label = "角色离线消息")
    public static final String PLAYER_OFFLINE_CHAT = "playerOfflineChat";
    /** 玩家信息缓存(在线用户,角色) **/
    @CommonAnnotation(label = "玩家信息缓存(在线用户,角色) ")
    public static final String PLAYER_ONLINE_CACHE = "playerOnlineCache";
    /** 角色在线消息 * */
    @CommonAnnotation(label = "角色在线消息")
    public static final String PLAYER_ONLINE_CHAT = "playerOnlineChat";
    /** 玩家当前的ID，对应的角色缓存 */
    @CommonAnnotation(label = "玩家当前的ID，对应的角色缓存")
    public static final String PLAYER_ROLE_MAP = "playerRoleMap";
    /************** 拍卖关联物品对象缓存 ***************************/
    @CommonAnnotation(label = "拍卖关联物品对象缓存")
    public static final String PLAYER_SALE_ITEM_MAP = "playerSaleItemMap";
    /************** 拍卖物品缓存 **********************************/
    @CommonAnnotation(label = "拍卖物品缓存")
    public static final String PLAYER_SALE_MAP = "playerSaleMap";

    /** 玩家Session缓存 **/
    @CommonAnnotation(label = "玩家Session缓存 ")
    public static final String PLAYER_SESSION_MAP = "playerSessionMap";

    /** 玩家签到物品配置 */
    @CommonAnnotation(label = "玩家签到物品配置")
    public static final String PLAYER_SIGN_ITEM = "playerSignItem";
    /**** 培养系统缓存 ****/
    @CommonAnnotation(label = "培养系统缓存")
    public static final String PLAYER_TRAIN_MAP = "playerTrainMap";
    /** 购买体力配置 */
    @CommonAnnotation(label = "购买体力配置")
    public static final String POWER_VIP = "powerVip";
    /** 许愿树配置缓存 * */
    @CommonAnnotation(label = "许愿树配置缓存")
    public static final String PRAY_TREE = "praytree";
    /** 军团战斗力排行100名缓存 */
    @CommonAnnotation(label = "军团战斗力排行100名缓存")
    public static final String RANGE_ARMY_FIGHT = "rangeArmyFight";
    /** 军团繁荣度排行100名缓存 */
    @CommonAnnotation(label = "军团繁荣度排行100名缓存")
    public static final String RANGER_ARMY_DEVELOP = "rangeArmyDevelop";
    /** 玩家战斗力排行100名缓存 */
    @CommonAnnotation(label = "玩家战斗力排行100名缓存")
    public static final String RANGER_FIGHT = "rangeFight";
    /** 玩家等级排行100名缓存 */
    @CommonAnnotation(label = "玩家等级排行100名缓存")
    public static final String RANGER_LEVEL = "rangeLevel";
    /** 玩家财富排行100名缓存 */
    @CommonAnnotation(label = "玩家财富排行100名缓存")
    public static final String RANGER_SILVER = "rangeSilver";
    /************** 日常任务刷新时间 ****************************************/
    @CommonAnnotation(label = "日常任务刷新时间")
    public static final String REFRESH_DAILY_TASK_TIME = "refreshDailyTaskTime";
    /**
     * 充值 id 职业 奖品
     * */
    @CommonAnnotation(label = "充值  id 职业 奖品")
    public static final String REWARD_OPP_PAY = "rewardOppPay";
    /** 巅峰对决 */
    @CommonAnnotation(label = "巅峰对决 ")
    public static final String RING_BATTLE_REWARD = "ringBattleReward";
    /** 巅峰对决基础数据类 */
    @CommonAnnotation(label = "*巅峰对决基础数据类 ")
    public static final String RING_CONFIG = "ringConfig";
    /**** 巅峰对决缓存 ***/
    @CommonAnnotation(label = "巅峰对决缓存")
    public static final String RING_MAP = "ringMap";
    /** 单人副本对象缓存 **/
    @CommonAnnotation(label = "单人副本对象缓存 ")
    public static final String SINGLE_GAME_COPY = "singelGameCopy";

    /** command指令包队列 * */
    @CommonAnnotation(label = "command指令包队列 ")
    public static final String SOCKET_IN_QUEUE = "commandQueue";

    /** socket包队列 * */
    @CommonAnnotation(label = "socket包队列 ")
    public static final String SOCKET_OUT_QUEUE = "socketQueue";

    /** 特殊商店物品 * */
    @CommonAnnotation(label = "特殊商店物品 ")
    public static final String SPECIAL_STORE_MAP = "specialStoreMap";

    /********** 强化石对应的价值因子缓存 ************/
    @CommonAnnotation(label = "强化石对应的价值因子缓存")
    public static final String STONE_WORTH_MAP = "stoneWorthMap";
    /** 商店物品 * */
    @CommonAnnotation(label = "商店物品 ")
    public static final String STORE_MAP = "storeMap";
    /********** 强化加成因子缓存 ************/
    @CommonAnnotation(label = "")
    public static final String STRENGTH_FACTOR_MAP = "strengthFactorMap";
    /** ======================== 缓存操作常量 ======================== **/
    /** 系统参数 **/
    @CommonAnnotation(label = "系统参数")
    public static final String SYSTEM_PARAM = "systemParam";
    /** 称号配置 */
    @CommonAnnotation(label = "称号配置 ")
    public static final String TITLE = "title";

    /*** 试炼之地map缓存 ***/
    @CommonAnnotation(label = "试炼之地map缓存")
    public static final String TRIALS_LAND_MAP = "trialsLandMap";
    /**** 试炼之地 掉落消息 ********/
    @CommonAnnotation(label = "试炼之地 掉落消息")
    public static final String TRIALSLAND_DROP_MSG = "trialsLandDropMsg";
    /** 版本更新信息 */
    @CommonAnnotation(label = "版本更新信息")
    public static final String VERSION_INFO = "versionInfo";
    /**** 竞技场连胜奖励 *****************/
    @CommonAnnotation(label = "竞技场连胜奖励")
    public static final String VICTORY_ARENA_MAP = "victoryArenaMap";
    /***** 洗练消耗缓存 ****************/
    @CommonAnnotation(label = "洗练消耗缓存")
    public static final String WASH_COST = "washCost";
    /********** 洗练因子相关缓存 *********************/
    @CommonAnnotation(label = "洗练因子相关缓存")
    public static final String WASH_FACTOR_LIST = "washFactorList";
    /** 世界boss对象缓存 **/
    @CommonAnnotation(label = "世界boss对象缓存")
    public static final String WORLD_BOSS = "worldBoss";
    /** 世界Boss数据缓存对象 **/
    @CommonAnnotation(label = "世界Boss数据缓存对象")
    public static final String WORLD_BOSS_MAP = "worldBossMap";
    /** 分析-玩家留存率 */
    @CommonAnnotation(label = "分析-玩家留存率")
    public static final String Z_KEEP_MAP = "zKeepMap";

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

    /**
     * 加载class时进行反射处理
     * 
     * @throws Exception
     */
    public static void initTypeArray() throws Exception {
	Class<?> c = CacheConstant.class;
	Field[] fs = c.getDeclaredFields();
	for (int i = 0; i < fs.length; i++) {
	    Field f = fs[i];
	    CommonAnnotation cache = f.getAnnotation(CommonAnnotation.class);
	    if (cache != null) {
		String label = cache.label();
		String value = (String) f.get(null);
		maptype.put(value, label);
	    }

	}
    }

}
