package com.jcwx.game.common.constants;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jcwx.game.web.CommonAnnotation;

/**
 * 功能节点解析
 * 
 * @author csp
 * 
 */
public class FunctionNodeConstant {

    public static Map<String, String> FunctionNode = new LinkedHashMap<String, String>();

    private static Logger logger = Logger.getLogger(PtServerConstant.class);

    @CommonAnnotation(label = "")
    public static final String n = "";

    @CommonAnnotation(label = "进入游戏登陆界面前读条25%")
    public static final String n10001 = "10001";

    @CommonAnnotation(label = "进入游戏登陆界面前读条50%")
    public static final String n10002 = "10002";

    @CommonAnnotation(label = "进入游戏登陆界面前读条75%")
    public static final String n10003 = "10003";

    @CommonAnnotation(label = "进入游戏登陆界面前读条100%")
    public static final String n10004 = "10004";

    @CommonAnnotation(label = "点击开始游戏按钮")
    public static final String n10005 = "10005";

    @CommonAnnotation(label = "点击服务器选择按钮")
    public static final String n10006 = "10006";

    @CommonAnnotation(label = "点击服务器进入游戏（和上面这个有所区别，是具体选择服务器进游戏的）")
    public static final String n10007 = "10007";

    @CommonAnnotation(label = "点击一键注册按钮")
    public static final String n10008 = "10008";

    @CommonAnnotation(label = "角色创建界面选择灵猴")
    public static final String n10009 = "10009";

    @CommonAnnotation(label = "角色创建界面选择神君")
    public static final String n10010 = "10010";

    @CommonAnnotation(label = "角色选择界面选择玄女")
    public static final String n10011 = "10011";

    @CommonAnnotation(label = "角色界面点击返回按钮")
    public static final String n10012 = "10012";

    @CommonAnnotation(label = "创建角色完成")
    public static final String n10013 = "10013";

    @CommonAnnotation(label = "创建角色到新手剧情过程中的加载30%")
    public static final String n10014 = "10014";

    @CommonAnnotation(label = "创建角色到新手剧情过程中的加载50%")
    public static final String n10015 = "10015";

    @CommonAnnotation(label = "创建角色到新手剧情过程中的加载75%")
    public static final String n10016 = "10016";

    @CommonAnnotation(label = "创建角色到新手剧情过程中的加载100%")
    public static final String n10017 = "10017";

    @CommonAnnotation(label = "新手剧情1中点击")
    public static final String n10018 = "10018";

    @CommonAnnotation(label = "新手剧情中进行移动操作")
    public static final String n10019 = "10019";

    @CommonAnnotation(label = "新手剧情中使用平砍")
    public static final String n10020 = "10020";

    @CommonAnnotation(label = "新手剧情中使用技能1")
    public static final String n10021 = "10021";

    @CommonAnnotation(label = "新手剧情中使用技能2")
    public static final String n10022 = "10022";

    @CommonAnnotation(label = "新手剧情中使用技能3")
    public static final String n10023 = "10023";

    @CommonAnnotation(label = "新手剧情中使用技能4")
    public static final String n10024 = "10024";

    @CommonAnnotation(label = "新手剧情中使用技能5")
    public static final String n10025 = "10025";

    @CommonAnnotation(label = "进入起名流程")
    public static final String n10026 = "10026";

    @CommonAnnotation(label = "点击随机起名")
    public static final String n10027 = "10027";

    @CommonAnnotation(label = "结束起名流程")
    public static final String n10028 = "10028";

    @CommonAnnotation(label = "二次确认框中点击确定")
    public static final String n10029 = "10029";

    @CommonAnnotation(label = "二次确认框中点击取消")
    public static final String n10030 = "10030";

    @CommonAnnotation(label = "新手剧情2中点击")
    public static final String n10031 = "10031";

    @CommonAnnotation(label = "新手剧情完成")
    public static final String n10032 = "10032";

    @CommonAnnotation(label = "新手剧情到主城过程中的加载25%")
    public static final String n10033 = "10033";

    @CommonAnnotation(label = "新手剧情到主城过程中的加载50%")
    public static final String n10034 = "10034";

    @CommonAnnotation(label = "新手剧情到主城过程中的加载75%")
    public static final String n10035 = "10035";

    @CommonAnnotation(label = "新手剧情到主城过程中的加载100%")
    public static final String n10036 = "10036";

    @CommonAnnotation(label = "点击在线奖励按钮")
    public static final String n10037 = "10037";

    @CommonAnnotation(label = "未接受1001（能用且用）任务时进行人物移动的操作")
    public static final String n10038 = "10038";

    @CommonAnnotation(label = "未接受1001（能用且用）任务时点击其他功能模块的操作")
    public static final String n10039 = "10039";

    @CommonAnnotation(label = "未接受1001（能用且用）任务时点击其他NPC")
    public static final String n10040 = "10040";

    @CommonAnnotation(label = "未接受1001（能用且用）任务时点击追踪按钮打开小猴子交互界面")
    public static final String n10041 = "10041";

    @CommonAnnotation(label = "未接受1001（能用且用）任务时其他方式打开小猴子交互界面")
    public static final String n10042 = "10042";

    @CommonAnnotation(label = "未接受1001（能用且用）任务时在小猴子交互界面点击选择1001任务")
    public static final String n10043 = "10043";

    @CommonAnnotation(label = "接受1001（能用且用）任务")
    public static final String n10044 = "10044";

    @CommonAnnotation(label = "点击功能弹性按钮")
    public static final String n10045 = "10045";

    @CommonAnnotation(label = "点击背包按钮")
    public static final String n10046 = "10046";

    @CommonAnnotation(label = "穿戴武器")
    public static final String n10047 = "10047";

    @CommonAnnotation(label = "穿戴衣服")
    public static final String n10048 = "10048";

    @CommonAnnotation(label = "点击关闭背包按钮")
    public static final String n10049 = "10049";

    @CommonAnnotation(label = "已接受1001任务时点击追踪按钮打开小猴子交互界面")
    public static final String n10050 = "10050";

    @CommonAnnotation(label = "已接受1001任务时其他方式打开小猴子交互界面")
    public static final String n10051 = "10051";

    @CommonAnnotation(label = "已接受1001任务时在小猴子交互界面点击选择1001任务")
    public static final String n10052 = "10052";

    @CommonAnnotation(label = "完成1001任务")
    public static final String n10053 = "10053";

    @CommonAnnotation(label = "弹出精彩活动浮窗")
    public static final String n10054 = "10054";

    @CommonAnnotation(label = "点击精彩活动浮窗使其飞到左边")
    public static final String n10055 = "10055";

    @CommonAnnotation(label = "点击精彩活动查看内容")
    public static final String n10056 = "10056";

    @CommonAnnotation(label = "点击精彩活动关闭按钮")
    public static final String n10057 = "10057";

    @CommonAnnotation(label = "未接受1002（腹肌）任务时点击追踪按钮打开小猴子交互界面")
    public static final String n10058 = "10058";

    @CommonAnnotation(label = "未接受1002任务时其他方式打开小猴子交互界面")
    public static final String n10059 = "10059";

    @CommonAnnotation(label = "未接受1002任务时在小猴子交互界面点击选择1002任务")
    public static final String n10060 = "10060";

    @CommonAnnotation(label = "接受1002任务")
    public static final String n10061 = "10061";

    @CommonAnnotation(label = "已接受1002任务时打开花果山副本选择界面")
    public static final String n10062 = "10062";

    @CommonAnnotation(label = "已接受1002任务时进入花果山山腰副本（点击开始战斗按钮）")
    public static final String n10063 = "10063";

    @CommonAnnotation(label = "某某某剧情中点击xxxxxxx")
    public static final String n10064 = "10064";

    @CommonAnnotation(label = "已接受1002任务时进入花果山山腰副本使用平砍")
    public static final String n10065 = "10065";

    @CommonAnnotation(label = "已接受1002任务时进入花果山山腰副本使用技能1")
    public static final String n10066 = "10066";

    @CommonAnnotation(label = "已接受1002任务时通关花果山山腰副本选择奖励")
    public static final String n10067 = "10067";

    @CommonAnnotation(label = "已接受1002任务时通关花果山山腰副本点击再战一次")
    public static final String n10068 = "10068";

    @CommonAnnotation(label = "已接受1002任务时通关花果山山腰副本点击离开副本")
    public static final String n10069 = "10069";

    @CommonAnnotation(label = "完成任务条件读条")
    public static final String n10070 = "10070";

    @CommonAnnotation(label = "已接受1002任务时点击追踪按钮打开小猴子交互界面")
    public static final String n10071 = "10071";

    @CommonAnnotation(label = "已接受1002任务时其他方式打开小猴子交互界面")
    public static final String n10072 = "10072";

    @CommonAnnotation(label = "已接受1002任务时在小猴子交互界面点击选择1002任务")
    public static final String n10073 = "10073";

    @CommonAnnotation(label = "完成1002任务")
    public static final String n10074 = "10074";

    @CommonAnnotation(label = "弹出技能浮窗")
    public static final String n10075 = "10075";

    @CommonAnnotation(label = "点击弹出技能浮窗")
    public static final String n10076 = "10076";

    @CommonAnnotation(label = "未接受1004（无主之地）任务时点击追踪按钮打开小猴子交互界面")
    public static final String n10077 = "10077";

    @CommonAnnotation(label = "未接受1004任务时其他方式打开小猴子交互界面")
    public static final String n10078 = "10078";

    @CommonAnnotation(label = "未接受1004任务时在小猴子交互界面点击选择1004任务")
    public static final String n10079 = "10079";

    @CommonAnnotation(label = "接受1004任务")
    public static final String n10080 = "10080";

    @CommonAnnotation(label = "已接受1004任务时打开花果山副本选择界面")
    public static final String n10081 = "10081";

    @CommonAnnotation(label = "已接受1004任务时进入水帘洞副本")
    public static final String n10082 = "10082";

    @CommonAnnotation(label = "某某某剧情中点击xxxxxxx")
    public static final String n10083 = "10083";

    @CommonAnnotation(label = "弹出技能获得浮窗")
    public static final String n10084 = "10084";

    @CommonAnnotation(label = "点击技能获得浮窗")
    public static final String n10085 = "10085";

    @CommonAnnotation(label = "已接受1004任务时进入水帘洞副本使用技能2")
    public static final String n10086 = "10086";

    @CommonAnnotation(label = "已接受1004任务时通关水帘洞副本选择奖励")
    public static final String n10087 = "10087";

    @CommonAnnotation(label = "已接受1004任务时通关水帘洞副本点击离开副本")
    public static final String n10088 = "10088";

    @CommonAnnotation(label = "已接受1004任务时点击追踪按钮打开老猴子交互界面")
    public static final String n10089 = "10089";

    @CommonAnnotation(label = "已接受1004任务时其他方式打开老猴子交互界面")
    public static final String n10090 = "10090";

    @CommonAnnotation(label = "已接受1004任务时在老猴子交互界面点击选择1004任务")
    public static final String n10091 = "10091";

    @CommonAnnotation(label = "完成1004任务")
    public static final String n10092 = "10092";

    @CommonAnnotation(label = "未接受1005（海边探路）任务时点击追踪按钮打开小猴子交互界面")
    public static final String n10093 = "10093";

    @CommonAnnotation(label = "未接受1005任务时其他方式打开小猴子交互界面")
    public static final String n10094 = "10094";

    @CommonAnnotation(label = "未接受1005任务时在小猴子交互界面点击选择1005任务")
    public static final String n10095 = "10095";

    @CommonAnnotation(label = "接受1005任务")
    public static final String n10096 = "10096";

    @CommonAnnotation(label = "已接受1005任务时打开花果山副本选择界面")
    public static final String n10097 = "10097";

    @CommonAnnotation(label = "已接受1007任务时点击选择其他非龙宫深处副本")
    public static final String n10098 = "10098";

    @CommonAnnotation(label = "已接受1005任务时进入东海海滨副本")
    public static final String n10099 = "10099";

    @CommonAnnotation(label = "已接受1005任务时进入东海海滨副本使用技能2")
    public static final String n10100 = "10100";

    @CommonAnnotation(label = "已接受1005任务时通关东海海滨副本选择奖励")
    public static final String n10101 = "10101";

    @CommonAnnotation(label = "已接受1005任务时通关东海海滨副本点击离开副本")
    public static final String n10102 = "10102";

    @CommonAnnotation(label = "已接受1005任务时点击追踪按钮打开老猴子交互界面")
    public static final String n10103 = "10103";

    @CommonAnnotation(label = "已接受1005任务时其他方式打开老猴子交互界面")
    public static final String n10104 = "10104";

    @CommonAnnotation(label = "已接受1005任务时在老猴子交互界面点击选择1005任务")
    public static final String n10105 = "10105";

    @CommonAnnotation(label = "完成1005任务")
    public static final String n10106 = "10106";

    @CommonAnnotation(label = "弹出锻造浮动窗口")
    public static final String n10107 = "10107";

    @CommonAnnotation(label = "点击锻造浮动窗口")
    public static final String n10108 = "10108";

    @CommonAnnotation(label = "弹出强化浮动窗口")
    public static final String n10109 = "10109";

    @CommonAnnotation(label = "点击强化浮动窗口")
    public static final String n10110 = "10110";

    @CommonAnnotation(label = "未接受1093任务时点击追踪按钮打开灵玉交互界面")
    public static final String n10111 = "10111";

    @CommonAnnotation(label = "未接受1093任务时在灵玉交互界面点击选择1093任务")
    public static final String n10112 = "10112";

    @CommonAnnotation(label = "接受1093任务")
    public static final String n10113 = "10113";

    @CommonAnnotation(label = "已接受1093任务时点击右下角弹性按钮")
    public static final String n10114 = "10114";

    @CommonAnnotation(label = "已接受1093任务时点击锻造按钮")
    public static final String n10115 = "10115";

    @CommonAnnotation(label = "已接受1093任务时点击强化按钮")
    public static final String n10116 = "10116";

    @CommonAnnotation(label = "出现金钱不足指引")
    public static final String n10117 = "10117";

    @CommonAnnotation(label = "点击金钱指引前往其他功能点")
    public static final String n10118 = "10118";

    @CommonAnnotation(label = "关闭金钱不足指引")
    public static final String n10119 = "10119";

    @CommonAnnotation(label = "已接受1093任务并打开强化界面时点击关闭按钮")
    public static final String n10120 = "10120";

    @CommonAnnotation(label = "已接受1093任务时点击追踪按钮打开灵玉交互界面")
    public static final String n10121 = "10121";

    @CommonAnnotation(label = "已接受1093任务时其他方式打开灵玉交互界面")
    public static final String n10122 = "10122";

    @CommonAnnotation(label = "已接受1093任务时在灵玉交互界面点击选择1093任务")
    public static final String n10123 = "10123";

    @CommonAnnotation(label = "完成1093任务")
    public static final String n10124 = "10124";

    @CommonAnnotation(label = "未接受1006任务时点击追踪按钮打开小狸交互界面")
    public static final String n10125 = "10125";

    @CommonAnnotation(label = "未接受1006任务时其他方式打开小狸交互界面")
    public static final String n10126 = "10126";

    @CommonAnnotation(label = "未接受1006任务时在小狸交互界面点击选择1006任务")
    public static final String n10127 = "10127";

    @CommonAnnotation(label = "接受1006任务")
    public static final String n10128 = "10128";

    @CommonAnnotation(label = "已接受1006任务时打开花果山副本选择界面")
    public static final String n10129 = "10129";

    @CommonAnnotation(label = "已接受1006任务时点击选择其他非龙宫深处副本")
    public static final String n10130 = "10130";

    @CommonAnnotation(label = "已接受1006任务时进入龙宫深处副本")
    public static final String n10131 = "10131";

    @CommonAnnotation(label = "已接受1006任务时被龙王击杀")
    public static final String n10132 = "10132";

    @CommonAnnotation(label = "已接受1006任务时通关龙宫深处副本选择奖励")
    public static final String n10133 = "10133";

    @CommonAnnotation(label = "已接受1006任务时通关龙宫深处副本点击离开副本")
    public static final String n10134 = "10134";

    @CommonAnnotation(label = "已接受1006任务时点击追踪按钮打开猴王交互界面")
    public static final String n10135 = "10135";

    @CommonAnnotation(label = "已接受1006任务时其他方式打开猴王交互界面")
    public static final String n10136 = "10136";

    @CommonAnnotation(label = "已接受1006任务时在猴王交互界面点击选择1093任务")
    public static final String n10137 = "10137";

    @CommonAnnotation(label = "完成1006任务")
    public static final String n10138 = "10138";

    @CommonAnnotation(label = "未接受1007任务时点击追踪按钮打开猴王交互界面")
    public static final String n10139 = "10139";

    @CommonAnnotation(label = "未接受1007任务时其他方式打开猴王交互界面")
    public static final String n10140 = "10140";

    @CommonAnnotation(label = "未接受1007任务时在猴王交互界面点击选择1007任务")
    public static final String n10141 = "10141";

    @CommonAnnotation(label = "接受1007任务")
    public static final String n10142 = "10142";

    @CommonAnnotation(label = "已接受1007任务时打开花果山副本选择界面")
    public static final String n10143 = "10143";

    @CommonAnnotation(label = "已接受1007任务时点击选择其他非鬼门关副本")
    public static final String n10144 = "10144";

    @CommonAnnotation(label = "已接受1007任务时进入鬼门关副本")
    public static final String n10145 = "10145";

    @CommonAnnotation(label = "已接受1007任务时在鬼门关副本死亡")
    public static final String n10146 = "10146";

    @CommonAnnotation(label = "已接受1007任务时通关鬼门关副本选择奖励")
    public static final String n10147 = "10147";

    @CommonAnnotation(label = "已接受1007任务时通关鬼门关副本点击离开副本")
    public static final String n10148 = "10148";

    @CommonAnnotation(label = "已接受1007任务时点击追踪按钮打开猴王交互界面")
    public static final String n10149 = "10149";

    @CommonAnnotation(label = "已接受1007任务时其他方式打开猴王交互界面")
    public static final String n10150 = "10150";

    @CommonAnnotation(label = "已接受1007任务时在猴王交互界面点击选择1093任务")
    public static final String n10151 = "10151";

    @CommonAnnotation(label = "完成1007任务")
    public static final String n10152 = "10152";

    @CommonAnnotation(label = "未接受1094任务时点击追踪按钮打开小猴子交互界面")
    public static final String n10153 = "10153";

    @CommonAnnotation(label = "未接受1094任务时其他方式打开小猴子交互界面")
    public static final String n10154 = "10154";

    @CommonAnnotation(label = "未接受1094任务时在小猴子交互界面点击选择1094任务")
    public static final String n10155 = "10155";

    @CommonAnnotation(label = "接受1094任务")
    public static final String n10156 = "10156";

    @CommonAnnotation(label = "已接受1094任务时点击弹性按钮")
    public static final String n10157 = "10157";

    @CommonAnnotation(label = "已接受1094任务时打开技能界面")
    public static final String n10158 = "10158";

    @CommonAnnotation(label = "已接受1094任务时提升技能等级")
    public static final String n10159 = "10159";

    @CommonAnnotation(label = "已接受1094任务时点击追踪按钮打开小猴子交互界面")
    public static final String n10160 = "10160";

    @CommonAnnotation(label = "已接受1094任务时其他方式打开小猴子交互界面")
    public static final String n10161 = "10161";

    @CommonAnnotation(label = "已接受1094任务时在小猴子交互界面点击选择1094任务")
    public static final String n10162 = "10162";

    @CommonAnnotation(label = "完成1094任务")
    public static final String n10163 = "10163";

    @CommonAnnotation(label = "未接受1008任务时点击追踪按钮打开猴王交互界面")
    public static final String n10164 = "10164";

    @CommonAnnotation(label = "未接受1008任务时其他方式打开猴王交互界面")
    public static final String n10165 = "10165";

    @CommonAnnotation(label = "未接受1008任务时在猴王交互界面点击选择1008任务")
    public static final String n10166 = "10166";

    @CommonAnnotation(label = "接受1008任务")
    public static final String n10167 = "10167";

    @CommonAnnotation(label = "已接受1008任务时打开花果山副本选择界面")
    public static final String n10168 = "10168";

    @CommonAnnotation(label = "已接受1008任务时点击选择其他非黄泉路副本")
    public static final String n10169 = "10169";

    @CommonAnnotation(label = "已接受1008任务时进入黄泉路副本")
    public static final String n10170 = "10170";

    @CommonAnnotation(label = "已接受1008任务时在黄泉路副本使用技能4")
    public static final String n10171 = "10171";

    @CommonAnnotation(label = "已接受1008任务时在黄泉路副本死亡")
    public static final String n10172 = "10172";

    @CommonAnnotation(label = "已接受1008任务时通关黄泉路副本选择奖励")
    public static final String n10173 = "10173";

    @CommonAnnotation(label = "已接受1008任务时通关黄泉路副本点击离开副本")
    public static final String n10174 = "10174";

    @CommonAnnotation(label = "已接受1008任务时点击追踪按钮打开猴王交互界面")
    public static final String n10175 = "10175";

    @CommonAnnotation(label = "已接受1008任务时其他方式打开猴王交互界面")
    public static final String n10176 = "10176";

    @CommonAnnotation(label = "已接受1008任务时在猴王交互界面点击选择1008任务")
    public static final String n10177 = "10177";

    @CommonAnnotation(label = "完成1008任务")
    public static final String n10178 = "10178";

    @CommonAnnotation(label = "未接受1009任务时点击追踪按钮打开猴王交互界面")
    public static final String n10179 = "10179";

    @CommonAnnotation(label = "未接受1009任务时其他方式打开猴王交互界面")
    public static final String n10180 = "10180";

    @CommonAnnotation(label = "未接受1009任务时在猴王交互界面点击选择1009任务")
    public static final String n10181 = "10181";

    @CommonAnnotation(label = "接受1009任务")
    public static final String n10182 = "10182";

    @CommonAnnotation(label = "已接受1009任务时打开花果山副本选择界面")
    public static final String n10183 = "10183";

    @CommonAnnotation(label = "已接受1009任务时点击选择其他非地狱十八层副本")
    public static final String n10184 = "10184";

    @CommonAnnotation(label = "已接受1009任务时进入地狱十八层副本")
    public static final String n10185 = "10185";

    @CommonAnnotation(label = "已接受1009任务时在地狱十八层副本使用技能5")
    public static final String n10186 = "10186";

    @CommonAnnotation(label = "已接受1009任务时在地狱十八层副本死亡")
    public static final String n10187 = "10187";

    @CommonAnnotation(label = "已接受1009任务时通关地狱十八层副本选择奖励")
    public static final String n10188 = "10188";

    @CommonAnnotation(label = "已接受1009任务时通关地狱十八层副本点击离开副本")
    public static final String n10189 = "10189";

    @CommonAnnotation(label = "已接受1009任务时点击追踪按钮打开猴王交互界面")
    public static final String n10190 = "10190";

    @CommonAnnotation(label = "已接受1009任务时其他方式打开猴王交互界面")
    public static final String n10191 = "10191";

    @CommonAnnotation(label = "已接受1009任务时在猴王交互界面点击选择1009任务")
    public static final String n10192 = "10192";

    @CommonAnnotation(label = "完成1009任务")
    public static final String n10193 = "10193";

    @CommonAnnotation(label = "未接受1010任务时点击追踪按钮打开猴王交互界面")
    public static final String n10194 = "10194";

    @CommonAnnotation(label = "未接受1010任务时其他方式打开猴王交互界面")
    public static final String n10195 = "10195";

    @CommonAnnotation(label = "未接受1010任务时在猴王交互界面点击选择1010任务")
    public static final String n10196 = "10196";

    @CommonAnnotation(label = "接受1010任务")
    public static final String n10197 = "10197";

    @CommonAnnotation(label = "已接受1010任务时打开花果山副本选择界面")
    public static final String n10198 = "10198";

    @CommonAnnotation(label = "已接受1010任务时点击选择其他非南天门副本")
    public static final String n10199 = "10199";

    @CommonAnnotation(label = "已接受1010任务时进入南天门副本")
    public static final String n10200 = "10200";

    @CommonAnnotation(label = "已接受1010任务时在南天门副本死亡")
    public static final String n10201 = "10201";

    @CommonAnnotation(label = "已接受1010任务时通关南天门副本选择奖励")
    public static final String n10202 = "10202";

    @CommonAnnotation(label = "已接受1010任务时通关南天门副本点击离开副本")
    public static final String n10203 = "10203";

    @CommonAnnotation(label = "已接受1010任务时点击追踪按钮打开猴王交互界面")
    public static final String n10204 = "10204";

    @CommonAnnotation(label = "已接受1010任务时其他方式打开猴王交互界面")
    public static final String n10205 = "10205";

    @CommonAnnotation(label = "已接受1010任务时在猴王交互界面点击选择1010任务")
    public static final String n10206 = "10206";

    @CommonAnnotation(label = "完成1010任务")
    public static final String n10207 = "10207";

    @CommonAnnotation(label = "未接受1011任务时点击追踪按钮打开猴王交互界面")
    public static final String n10208 = "10208";

    @CommonAnnotation(label = "未接受1011任务时其他方式打开猴王交互界面")
    public static final String n10209 = "10209";

    @CommonAnnotation(label = "未接受1011任务时在猴王交互界面点击选择1011任务")
    public static final String n10210 = "10210";

    @CommonAnnotation(label = "接受1011任务")
    public static final String n10211 = "10211";

    @CommonAnnotation(label = "已接受1011任务时打开花果山副本选择界面")
    public static final String n10212 = "10212";

    @CommonAnnotation(label = "已接受1011任务时点击选择其他非凌霄殿副本")
    public static final String n10213 = "10213";

    @CommonAnnotation(label = "已接受1011任务时进入凌霄殿副本")
    public static final String n10214 = "10214";

    @CommonAnnotation(label = "已接受1011任务时在凌霄殿副本死亡")
    public static final String n10215 = "10215";

    @CommonAnnotation(label = "已接受1011任务时通关凌霄殿副本选择奖励")
    public static final String n10216 = "10216";

    @CommonAnnotation(label = "已接受1011任务时通关凌霄殿副本点击离开副本")
    public static final String n10217 = "10217";

    @CommonAnnotation(label = "已接受1011任务时点击追踪按钮打开猴王交互界面")
    public static final String n10218 = "10218";

    @CommonAnnotation(label = "已接受1011任务时其他方式打开猴王交互界面")
    public static final String n10219 = "10219";

    @CommonAnnotation(label = "已接受1011任务时在猴王交互界面点击选择1011任务")
    public static final String n10220 = "10220";

    @CommonAnnotation(label = "完成1011任务")
    public static final String n10221 = "10221";

    @CommonAnnotation(label = "打开世界地图界面")
    public static final String n10222 = "10222";

    @CommonAnnotation(label = "点击长安地图按钮")
    public static final String n10223 = "10223";

    @CommonAnnotation(label = "点击花果山地图按钮")
    public static final String n10224 = "10224";

    @CommonAnnotation(label = "点击宝象国地图按钮")
    public static final String n10225 = "10225";

    @CommonAnnotation(label = "进入长安地图加载进度条")
    public static final String n10226 = "10226";

    @CommonAnnotation(label = "未接受1012任务时点击追踪按钮打开花寻交互界面")
    public static final String n10227 = "10227";

    @CommonAnnotation(label = "未接受1012任务时其他方式打开花寻交互界面")
    public static final String n10228 = "10228";

    @CommonAnnotation(label = "未接受1012任务时在花寻交互界面点击选择1012任务")
    public static final String n10229 = "10229";

    @CommonAnnotation(label = "接受1012任务")
    public static final String n10230 = "10230";

    @CommonAnnotation(label = "已接受1012任务时打开长安副本选择界面")
    public static final String n10231 = "10231";

    @CommonAnnotation(label = "已接受1012任务时点击选择其他非凌霄殿副本")
    public static final String n10232 = "10232";

    @CommonAnnotation(label = "已接受1012任务时进入长安野外副本")
    public static final String n10233 = "10233";

    @CommonAnnotation(label = "已接受1012任务时在长安野外副本死亡")
    public static final String n10234 = "10234";

    @CommonAnnotation(label = "已接受1012任务时通关长安野外副本选择奖励")
    public static final String n10235 = "10235";

    @CommonAnnotation(label = "已接受1012任务时通关长安野外副本点击离开副本")
    public static final String n10236 = "10236";

    @CommonAnnotation(label = "已接受1012任务时点击追踪按钮打开花寻交互界面")
    public static final String n10237 = "10237";

    @CommonAnnotation(label = "已接受1012任务时其他方式打开花寻交互界面")
    public static final String n10238 = "10238";

    @CommonAnnotation(label = "已接受1012任务时在花寻交互界面点击选择1012任务")
    public static final String n10239 = "10239";

    @CommonAnnotation(label = "完成1012任务")
    public static final String n10240 = "10240";

    @CommonAnnotation(label = "背包界面点击道具")
    public static final String n11001 = "11001";

    @CommonAnnotation(label = "背包界面双击穿戴装备")
    public static final String n11002 = "11002";

    @CommonAnnotation(label = "背包界面双击使用道具")
    public static final String n11003 = "11003";

    @CommonAnnotation(label = "背包界面单击打开装备tips")
    public static final String n11004 = "11004";

    @CommonAnnotation(label = "背包界面单击打开道具tips")
    public static final String n11005 = "11005";

    @CommonAnnotation(label = "打开装备tips时点击穿戴装备")
    public static final String n11006 = "11006";

    @CommonAnnotation(label = "打开装备tips时点击强化装备")
    public static final String n11007 = "11007";

    @CommonAnnotation(label = "打开装备tips时点击继承装备")
    public static final String n11008 = "11008";

    @CommonAnnotation(label = "打开道具tips时点击使用道具")
    public static final String n11009 = "11009";

    @CommonAnnotation(label = "背包界面内点击出售按钮")
    public static final String n11010 = "11010";

    @CommonAnnotation(label = "背包界面内点击整理背包按钮")
    public static final String n11011 = "11011";

    @CommonAnnotation(label = "背包界面内点击回购按钮")
    public static final String n11012 = "11012";

    @CommonAnnotation(label = "出售功能时选中物品")
    public static final String n11013 = "11013";

    @CommonAnnotation(label = "出售功能时点击确定出售按钮")
    public static final String n11014 = "11014";

    @CommonAnnotation(label = "出售功能时点击返回按钮")
    public static final String n11015 = "11015";

    @CommonAnnotation(label = "背包界面内点击提升按钮")
    public static final String n11016 = "11016";

    @CommonAnnotation(label = "背包界面切换到属性面板")
    public static final String n11017 = "11017";

    @CommonAnnotation(label = "背包界面切换到称号面板")
    public static final String n11018 = "11018";

    @CommonAnnotation(label = "背包界面内点击培养按钮")
    public static final String n11019 = "11019";

    @CommonAnnotation(label = "背包界面内点击时装按钮")
    public static final String n11020 = "11020";

    @CommonAnnotation(label = "称号界面内点击使用称号")
    public static final String n11021 = "11021";

    @CommonAnnotation(label = "打开技能界面")
    public static final String n11022 = "11022";

    @CommonAnnotation(label = "技能界面内选择技能1")
    public static final String n11023 = "11023";

    @CommonAnnotation(label = "技能界面内选择技能2")
    public static final String n11024 = "11024";

    @CommonAnnotation(label = "技能界面内选择技能3")
    public static final String n11025 = "11025";

    @CommonAnnotation(label = "技能界面内选择技能4")
    public static final String n11026 = "11026";

    @CommonAnnotation(label = "技能界面内选择技能5")
    public static final String n11027 = "11027";

    @CommonAnnotation(label = "技能界面内选择1后点升级")
    public static final String n11028 = "11028";

    @CommonAnnotation(label = "技能界面内选择2后点升级")
    public static final String n11029 = "11029";

    @CommonAnnotation(label = "技能界面内选择3后点升级")
    public static final String n11030 = "11030";

    @CommonAnnotation(label = "技能界面内选择4后点升级")
    public static final String n11031 = "11031";

    @CommonAnnotation(label = "技能界面内选择5后点升级")
    public static final String n11032 = "11032";

    @CommonAnnotation(label = "打开强化界面")
    public static final String n11033 = "11033";

    @CommonAnnotation(label = "强化界面选择装备")
    public static final String n11034 = "11034";

    @CommonAnnotation(label = "强化界面选择装备点击强化")
    public static final String n11035 = "11035";

    @CommonAnnotation(label = "打开继承界面")
    public static final String n11036 = "11036";

    @CommonAnnotation(label = "继承界面内父装备槽放置装备")
    public static final String n11037 = "11037";

    @CommonAnnotation(label = "继承界面内子装备槽放置装备")
    public static final String n11038 = "11038";

    @CommonAnnotation(label = "点击继承按钮")
    public static final String n11039 = "11039";

    @CommonAnnotation(label = "打开打造界面")
    public static final String n11040 = "11040";

    @CommonAnnotation(label = "打造界面内选择打造的装备")
    public static final String n11041 = "11041";

    @CommonAnnotation(label = "打造界面内点击打造按钮")
    public static final String n11042 = "11042";

    @CommonAnnotation(label = "打造界面内点击材料图标")
    public static final String n11043 = "11043";

    @CommonAnnotation(label = "打开成就界面")
    public static final String n11044 = "11044";

    @CommonAnnotation(label = "成就界面内点击选择成就")
    public static final String n11045 = "11045";

    @CommonAnnotation(label = "成就界面内点击领取奖励")
    public static final String n11046 = "11046";

    @CommonAnnotation(label = "打开精英副本界面")
    public static final String n11047 = "11047";

    @CommonAnnotation(label = "精英副本界面点击创建房间")
    public static final String n11048 = "11048";

    @CommonAnnotation(label = "精英副本界面点击加入房间")
    public static final String n11049 = "11049";

    @CommonAnnotation(label = "精英副本界面点击购买次数")
    public static final String n11050 = "11050";

    @CommonAnnotation(label = "精英副本创建界面点击选择创建")
    public static final String n11051 = "11051";

    @CommonAnnotation(label = "精英副本创建界面点击选择购买次数")
    public static final String n11052 = "11052";

    @CommonAnnotation(label = "精英副本房间界面点击邀请")
    public static final String n11053 = "11053";

    @CommonAnnotation(label = "精英副本房间界面点击开始")
    public static final String n11054 = "11054";

    @CommonAnnotation(label = "打开三十六重天界面")
    public static final String n11055 = "11055";

    @CommonAnnotation(label = "三十六重天界面点击可以进入的关卡")
    public static final String n11056 = "11056";

    @CommonAnnotation(label = "三十六重天界面点击不可进入的关卡")
    public static final String n11057 = "11057";

    @CommonAnnotation(label = "三十六重天界面点击重置按钮")
    public static final String n11058 = "11058";

    @CommonAnnotation(label = "三十六重天界面点击扫荡按钮")
    public static final String n11059 = "11059";

    @CommonAnnotation(label = "打开登天路界面")
    public static final String n11060 = "11060";

    @CommonAnnotation(label = "选择挑战玩家")
    public static final String n11061 = "11061";

    @CommonAnnotation(label = "点击查看前十名")
    public static final String n11062 = "11062";

    @CommonAnnotation(label = "点击购买次数")
    public static final String n11063 = "11063";

    @CommonAnnotation(label = "打开挑战心魔界面")
    public static final String n11064 = "11064";

    @CommonAnnotation(label = "点击挑战")
    public static final String n11065 = "11065";

    @CommonAnnotation(label = "点击增加次数")
    public static final String n11066 = "11066";

    @CommonAnnotation(label = "打开日常任务界面")
    public static final String n11067 = "11067";

    @CommonAnnotation(label = "选择日常任务")
    public static final String n11068 = "11068";

    @CommonAnnotation(label = "日常任务进行升星")
    public static final String n11069 = "11069";

    @CommonAnnotation(label = "接受日常任务")
    public static final String n11070 = "11070";

    @CommonAnnotation(label = "完成日常任务")
    public static final String n11071 = "11071";

    @CommonAnnotation(label = "使用一键完成功能")
    public static final String n11072 = "11072";

    @CommonAnnotation(label = "打开星力召唤界面")
    public static final String n11073 = "11073";

    @CommonAnnotation(label = "点击召唤")
    public static final String n11074 = "11074";

    @CommonAnnotation(label = "点击免费改运")
    public static final String n11075 = "11075";

    @CommonAnnotation(label = "点击逆天改运")
    public static final String n11076 = "11076";

    @CommonAnnotation(label = "点击收获")
    public static final String n11077 = "11077";

    @CommonAnnotation(label = "打开点石成金界面")
    public static final String n11078 = "11078";

    @CommonAnnotation(label = "点击点石成金按钮")
    public static final String n11079 = "11079";

    @CommonAnnotation(label = "点击一件点金按钮")
    public static final String n11080 = "11080";

    @CommonAnnotation(label = "打开仙门斗法界面")
    public static final String n11081 = "11081";

    @CommonAnnotation(label = "在仙门斗法界面点击创建按钮")
    public static final String n11082 = "11082";

    @CommonAnnotation(label = "在仙门斗法界面点击加入按钮")
    public static final String n11083 = "11083";

    @CommonAnnotation(label = "在仙门斗法房间界面点击匹配")
    public static final String n11084 = "11084";

    @CommonAnnotation(label = "点击打开每日目标界面")
    public static final String n11085 = "11085";

    @CommonAnnotation(label = "领取第1档奖励")
    public static final String n11086 = "11086";

    @CommonAnnotation(label = "领取第2档奖励")
    public static final String n11087 = "11087";

    @CommonAnnotation(label = "领取第3档奖励")
    public static final String n11088 = "11088";

    @CommonAnnotation(label = "领取第4档奖励")
    public static final String n11089 = "11089";

    @CommonAnnotation(label = "花果山山腰死亡")
    public static final String n12001 = "12001";

    @CommonAnnotation(label = "花果山顶死亡")
    public static final String n12002 = "12002";

    @CommonAnnotation(label = "水帘洞死亡")
    public static final String n12003 = "12003";

    @CommonAnnotation(label = "东海小径死亡")
    public static final String n12004 = "12004";

    @CommonAnnotation(label = "龙宫深处死亡")
    public static final String n12005 = "12005";

    @CommonAnnotation(label = "鬼门关死亡")
    public static final String n12006 = "12006";

    @CommonAnnotation(label = "黄泉路死亡")
    public static final String n12007 = "12007";

    @CommonAnnotation(label = "地狱十八层死亡")
    public static final String n12008 = "12008";

    @CommonAnnotation(label = "南天门死亡")
    public static final String n12009 = "12009";

    @CommonAnnotation(label = "凌霄殿死亡")
    public static final String n12010 = "12010";

    @CommonAnnotation(label = "长安野外死亡")
    public static final String n12011 = "12011";

    @CommonAnnotation(label = "长安西郊死亡")
    public static final String n12012 = "12012";

    @CommonAnnotation(label = "西郊尽头死亡")
    public static final String n12013 = "12013";

    @CommonAnnotation(label = "蛇盘山下死亡")
    public static final String n12014 = "12014";

    @CommonAnnotation(label = "蛇盘山死亡")
    public static final String n12015 = "12015";

    @CommonAnnotation(label = "鹰愁涧死亡")
    public static final String n12016 = "12016";

    @CommonAnnotation(label = "观音禅院死亡")
    public static final String n12017 = "12017";

    @CommonAnnotation(label = "禅院内房死亡")
    public static final String n12018 = "12018";

    @CommonAnnotation(label = "禅院密室死亡")
    public static final String n12019 = "12019";

    @CommonAnnotation(label = "黑风山脚死亡")
    public static final String n12020 = "12020";

    @CommonAnnotation(label = "黑风山死亡")
    public static final String n12021 = "12021";

    @CommonAnnotation(label = "黑风洞外死亡")
    public static final String n12022 = "12022";

    @CommonAnnotation(label = "黑风洞死亡")
    public static final String n12023 = "12023";

    @CommonAnnotation(label = "洞深密道死亡")
    public static final String n12024 = "12024";

    @CommonAnnotation(label = "高老庄死亡")
    public static final String n12025 = "12025";

    @CommonAnnotation(label = "高老庄喜堂死亡")
    public static final String n12026 = "12026";

    @CommonAnnotation(label = "云栈洞死亡")
    public static final String n12027 = "12027";

    @CommonAnnotation(label = "浮屠山死亡")
    public static final String n12028 = "12028";

    @CommonAnnotation(label = "黄风岭山脚死亡")
    public static final String n12029 = "12029";

    @CommonAnnotation(label = "黄风岭死亡")
    public static final String n12030 = "12030";

    @CommonAnnotation(label = "小须弥死亡")
    public static final String n12031 = "12031";

    @CommonAnnotation(label = "黄风洞死亡")
    public static final String n12032 = "12032";

    @CommonAnnotation(label = "长安东郊死亡")
    public static final String n12033 = "12033";

    @CommonAnnotation(label = "流沙河边死亡")
    public static final String n12034 = "12034";

    @CommonAnnotation(label = "弱水三千死亡")
    public static final String n12035 = "12035";

    @CommonAnnotation(label = "流沙河对岸死亡")
    public static final String n12036 = "12036";

    @CommonAnnotation(label = "五庄观死亡")
    public static final String n12037 = "12037";

    @CommonAnnotation(label = "镇元子别院死亡")
    public static final String n12038 = "12038";

    @CommonAnnotation(label = "人参果树死亡")
    public static final String n12039 = "12039";

    @CommonAnnotation(label = "虚无幻境死亡")
    public static final String n12040 = "12040";

    @CommonAnnotation(label = "黑松林外死亡")
    public static final String n12041 = "12041";

    @CommonAnnotation(label = "黑松林死亡")
    public static final String n12042 = "12042";

    @CommonAnnotation(label = "黑松林秘洞死亡")
    public static final String n12043 = "12043";

    @CommonAnnotation(label = "宝象森罗死亡")
    public static final String n12044 = "12044";

    @CommonAnnotation(label = "森罗万象死亡")
    public static final String n12045 = "12045";

    @CommonAnnotation(label = "万象归一死亡")
    public static final String n12046 = "12046";

    @CommonAnnotation(label = "金光顶洞死亡")
    public static final String n12047 = "12047";

    @CommonAnnotation(label = "平顶山脚死亡")
    public static final String n12048 = "12048";

    @CommonAnnotation(label = "平顶山死亡")
    public static final String n12049 = "12049";

    @CommonAnnotation(label = "莲花洞外死亡")
    public static final String n12050 = "12050";

    @CommonAnnotation(label = "莲花洞死亡")
    public static final String n12051 = "12051";

    @CommonAnnotation(label = "压龙山死亡")
    public static final String n12052 = "12052";

    @CommonAnnotation(label = "破寺外死亡")
    public static final String n12053 = "12053";

    @CommonAnnotation(label = "破寺死亡")
    public static final String n12054 = "12054";

    @CommonAnnotation(label = "破寺幻境死亡")
    public static final String n12055 = "12055";

    @CommonAnnotation(label = "号山死亡")
    public static final String n12056 = "12056";

    @CommonAnnotation(label = "枯松涧死亡")
    public static final String n12057 = "12057";

    @CommonAnnotation(label = "火云洞死亡")
    public static final String n12058 = "12058";

    @CommonAnnotation(label = "火焰山脚死亡")
    public static final String n12059 = "12059";

    @CommonAnnotation(label = "火焰山死亡")
    public static final String n12060 = "12060";

    @CommonAnnotation(label = "冷泉死亡")
    public static final String n12061 = "12061";

    @CommonAnnotation(label = "翠云山死亡")
    public static final String n12062 = "12062";

    @CommonAnnotation(label = "芭蕉洞外死亡")
    public static final String n12063 = "12063";

    @CommonAnnotation(label = "芭蕉洞死亡")
    public static final String n12064 = "12064";

    @CommonAnnotation(label = "积雷山死亡")
    public static final String n12065 = "12065";

    @CommonAnnotation(label = "摩云洞死亡")
    public static final String n12066 = "12066";

    @CommonAnnotation(label = "玲珑宝地死亡")
    public static final String n12067 = "12067";

    @CommonAnnotation(label = "芭蕉洞藏宝室死亡")
    public static final String n12068 = "12068";

    @CommonAnnotation(label = "太清化境死亡")
    public static final String n12069 = "12069";

    @CommonAnnotation(label = "虚空幻境死亡")
    public static final String n12070 = "12070";

    @CommonAnnotation(label = "精英悟空幻想")
    public static final String n12071 = "12071";

    @CommonAnnotation(label = "精英龙王")
    public static final String n12072 = "12072";

    @CommonAnnotation(label = "精英判官")
    public static final String n12073 = "12073";

    @CommonAnnotation(label = "精英巨灵神")
    public static final String n12074 = "12074";

    @CommonAnnotation(label = "精英小白龙")
    public static final String n12075 = "12075";

    @CommonAnnotation(label = "精英主持")
    public static final String n12076 = "12076";

    @CommonAnnotation(label = "精英蛇女")
    public static final String n12077 = "12077";

    @CommonAnnotation(label = "精英八戒")
    public static final String n12078 = "12078";

    @CommonAnnotation(label = "精英沙僧")
    public static final String n12079 = "12079";

    @CommonAnnotation(label = "精英镇元子")
    public static final String n12080 = "12080";

    @CommonAnnotation(label = "精英黄袍怪")
    public static final String n12081 = "12081";

    @CommonAnnotation(label = "精英宝象公主")
    public static final String n12082 = "12082";

    @CommonAnnotation(label = "精英银角")
    public static final String n12083 = "12083";

    @CommonAnnotation(label = "精英红孩儿")
    public static final String n12084 = "12084";

    @CommonAnnotation(label = "精英铁扇公主")
    public static final String n12085 = "12085";

    @CommonAnnotation(label = "精英牛魔王")
    public static final String n12086 = "12086";

    @CommonAnnotation(label = "精英陆压")
    public static final String n12087 = "12087";

    @CommonAnnotation(label = "三十六重天第1层天死亡")
    public static final String n12088 = "12088";

    @CommonAnnotation(label = "三十六重天第2层天死亡")
    public static final String n12089 = "12089";

    @CommonAnnotation(label = "三十六重天第3层天死亡")
    public static final String n12090 = "12090";

    @CommonAnnotation(label = "三十六重天第4层天死亡")
    public static final String n12091 = "12091";

    @CommonAnnotation(label = "三十六重天第5层天死亡")
    public static final String n12092 = "12092";

    @CommonAnnotation(label = "三十六重天第6层天死亡")
    public static final String n12093 = "12093";

    @CommonAnnotation(label = "三十六重天第7层天死亡")
    public static final String n12094 = "12094";

    @CommonAnnotation(label = "三十六重天第8层天死亡")
    public static final String n12095 = "12095";

    @CommonAnnotation(label = "三十六重天第9层天死亡")
    public static final String n12096 = "12096";

    @CommonAnnotation(label = "三十六重天第10层天死亡")
    public static final String n12097 = "12097";

    @CommonAnnotation(label = "三十六重天第11层天死亡")
    public static final String n12098 = "12098";

    @CommonAnnotation(label = "三十六重天第12层天死亡")
    public static final String n12099 = "12099";

    @CommonAnnotation(label = "心魔第1关死亡")
    public static final String n12100 = "12100";

    @CommonAnnotation(label = "心魔第2关死亡")
    public static final String n12101 = "12101";

    @CommonAnnotation(label = "心魔第3关死亡")
    public static final String n12102 = "12102";

    @CommonAnnotation(label = "心魔第4关死亡")
    public static final String n12103 = "12103";

    @CommonAnnotation(label = "心魔第5关死亡")
    public static final String n12104 = "12104";

    @CommonAnnotation(label = "心魔第6关死亡")
    public static final String n12105 = "12105";

    @CommonAnnotation(label = "心魔第7关死亡")
    public static final String n12106 = "12106";

    @CommonAnnotation(label = "心魔第8关死亡")
    public static final String n12107 = "12107";

    @CommonAnnotation(label = "心魔第9关死亡")
    public static final String n12108 = "12108";

    @CommonAnnotation(label = "心魔第10关死亡")
    public static final String n12109 = "12109";

    @CommonAnnotation(label = "心魔第11关死亡")
    public static final String n12110 = "12110";

    @CommonAnnotation(label = "心魔第12关死亡")
    public static final String n12111 = "12111";

    @CommonAnnotation(label = "心魔第13关死亡")
    public static final String n12112 = "12112";

    @CommonAnnotation(label = "心魔第14关死亡")
    public static final String n12113 = "12113";

    @CommonAnnotation(label = "心魔第15关死亡")
    public static final String n12114 = "12114";

    @CommonAnnotation(label = "心魔第16关死亡")
    public static final String n12115 = "12115";

    @CommonAnnotation(label = "心魔第17关死亡")
    public static final String n12116 = "12116";

    @CommonAnnotation(label = "心魔第18关死亡")
    public static final String n12117 = "12117";

    @CommonAnnotation(label = "心魔第19关死亡")
    public static final String n12118 = "12118";

    @CommonAnnotation(label = "心魔第20关死亡")
    public static final String n12119 = "12119";

    @CommonAnnotation(label = "心魔第21关死亡")
    public static final String n12120 = "12120";

    @CommonAnnotation(label = "心魔第22关死亡")
    public static final String n12121 = "12121";

    @CommonAnnotation(label = "心魔第23关死亡")
    public static final String n12122 = "12122";

    @CommonAnnotation(label = "心魔第24关死亡")
    public static final String n12123 = "12123";

    @CommonAnnotation(label = "心魔第25关死亡")
    public static final String n12124 = "12124";

    @CommonAnnotation(label = "心魔第26关死亡")
    public static final String n12125 = "12125";

    @CommonAnnotation(label = "心魔第27关死亡")
    public static final String n12126 = "12126";

    @CommonAnnotation(label = "心魔第28关死亡")
    public static final String n12127 = "12127";

    @CommonAnnotation(label = "心魔第29关死亡")
    public static final String n12128 = "12128";

    @CommonAnnotation(label = "心魔第30关死亡")
    public static final String n12129 = "12129";

    @CommonAnnotation(label = "心魔第31关死亡")
    public static final String n12130 = "12130";

    @CommonAnnotation(label = "心魔第32关死亡")
    public static final String n12131 = "12131";

    @CommonAnnotation(label = "心魔第33关死亡")
    public static final String n12132 = "12132";

    @CommonAnnotation(label = "心魔第34关死亡")
    public static final String n12133 = "12133";

    @CommonAnnotation(label = "心魔第35关死亡")
    public static final String n12134 = "12134";

    @CommonAnnotation(label = "心魔第36关死亡")
    public static final String n12135 = "12135";

    @CommonAnnotation(label = "心魔第37关死亡")
    public static final String n12136 = "12136";

    @CommonAnnotation(label = "心魔第38关死亡")
    public static final String n12137 = "12137";

    @CommonAnnotation(label = "心魔第39关死亡")
    public static final String n12138 = "12138";

    @CommonAnnotation(label = "心魔第40关死亡")
    public static final String n12139 = "12139";

    @CommonAnnotation(label = "心魔第41关死亡")
    public static final String n12140 = "12140";

    @CommonAnnotation(label = "心魔第42关死亡")
    public static final String n12141 = "12141";

    @CommonAnnotation(label = "心魔第43关死亡")
    public static final String n12142 = "12142";

    @CommonAnnotation(label = "心魔第44关死亡")
    public static final String n12143 = "12143";

    @CommonAnnotation(label = "心魔第45关死亡")
    public static final String n12144 = "12144";

    @CommonAnnotation(label = "心魔第46关死亡")
    public static final String n12145 = "12145";

    @CommonAnnotation(label = "心魔第47关死亡")
    public static final String n12146 = "12146";

    @CommonAnnotation(label = "心魔第48关死亡")
    public static final String n12147 = "12147";

    @CommonAnnotation(label = "心魔第49关死亡")
    public static final String n12148 = "12148";

    @CommonAnnotation(label = "心魔第50关死亡")
    public static final String n12149 = "12149";

    @CommonAnnotation(label = "登天路死亡")
    public static final String n12150 = "12150";

    @CommonAnnotation(label = "仙门斗法死亡")
    public static final String n12151 = "12151";

    @CommonAnnotation(label = "切磋死亡")
    public static final String n12152 = "12152";

    static {
	try {
	    initFunctionNodeArray();
	} catch (Exception e) {
	    logger.error("异常:", e);
	}
    }

    public static Map<String, String> getFunctionNodeMap() {
	return FunctionNode;
    }

    /**
     * 加载class时进行反射处理
     * 
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void initFunctionNodeArray() throws Exception {
	Class c = FunctionNodeConstant.class;
	Field[] fs = c.getDeclaredFields();
	for (int i = 0; i < fs.length; i++) {
	    Field f = fs[i];
	    CommonAnnotation pt = f.getAnnotation(CommonAnnotation.class);
	    if (pt != null) {
		String label = pt.label();
		String value = (String) f.get(null);
		FunctionNode.put(value, label);
	    }

	}
    }

}
