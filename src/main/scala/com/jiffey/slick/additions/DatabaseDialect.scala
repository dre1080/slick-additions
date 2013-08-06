package com.jiffey.slick.additions

/**
 * This is used usually when you have to use raw SQL on some rare occasion.
 * You have a DatabaseDialect with different driver implementations for raw SQL functions.
 * None implemented yet.
 *
 * @see [[http://eng.42go.com/using-database-dialects-with-in-play-framework/ Using Database Dialects]]
 *
 * {{{
 *   trait DatabaseDialect[T <: DataBaseComponent] {
 *     def day(date: DateTime): String
 *   }
 *
 *   object MySqlDatabaseDialect extends DatabaseDialect[MySQL] {
 *     def day(date: DateTime): String =
 *       s"""STR_TO_DATE('${date.toStandardDateString}', '%Y-%m-%d')"""
 *   }
 *
 *   object H2DatabaseDialect extends DatabaseDialect[H2] {
 *     def day(date: DateTime): String =
 *       s"""PARSEDATETIME('${date.toStandardDateString}', 'y-M-d')"""
 *   }
 * }}}
 *
 * @tparam T A DatabaseComponent type.
 */
trait DatabaseDialect[T <: DatabaseComponent]
