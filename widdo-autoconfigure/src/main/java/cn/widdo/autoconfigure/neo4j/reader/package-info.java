/**
 * widdo-autoconfigure中neo4j的reader包,包含抽象类和默认的实现类.
 *
 * thr abstract class is {@link cn.widdo.autoconfigure.neo4j.reader.AbstractNeo4jReaderDecorator}.
 *
 * the default implementation is {@link cn.widdo.autoconfigure.neo4j.reader.DefaultNeo4jReader}.
 *
 * And you can have implementation of yourself by extends  {@link cn.widdo.autoconfigure.neo4j.reader.AbstractNeo4jReaderDecorator},
 * like {@link cn.widdo.autoconfigure.neo4j.reader.CustomNeo4jReader}
 *
 * @author XYL
 * @date 2022/11/28 17:28:19
 * @since 263.1.1.0
 **/
package cn.widdo.autoconfigure.neo4j.reader;
