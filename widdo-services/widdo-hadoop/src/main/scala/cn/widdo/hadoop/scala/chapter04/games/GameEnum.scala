package cn.widdo.hadoop.scala.chapter04.games

/**
 * scala学习第四天-枚举
 *
 * 通过 trait 和 case object实现
 *
 * @author XYL
 * @date 2023/07/03 16:59
 * @since 305.2.2.0
 */
sealed trait GameEnum

/**
 * dota
 */
case object DOTA extends GameEnum

/**
 * 地下城与勇士
 */
case object DNF extends GameEnum

/**
 * 英雄联盟
 */
case object LOL extends GameEnum

/**
 * 坦克世界
 */
case object WOT extends GameEnum

/**
 * 梦三国
 */
case object MSG extends GameEnum
