package com.l.model;

import java.util.Collection;

/**
 * TreeNode
 *
 * @author Administrator
 * @since 2025/4/7 18:08
 */
public interface Node<T extends Node<T, V>, V> {

	/**
	 * 获取父节点
	 *
	 * @return 父节点
	 */
	T getParent();

	/**
	 * 子节点
	 *
	 * @return 子节点
	 */
	Collection<T> getChildren();

	/**
	 * 获取值
	 *
	 * @return 值
	 */
	V getValue();
}
