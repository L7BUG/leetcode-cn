package com.l.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * BaseTreeNode
 *
 * @author Administrator
 * @since 2025/4/8 10:37
 */
@Getter
@Setter
public class IdNode<I, V> implements Node<IdNode<I, V>, V> {

	private final I id;

	private final I parentId;

	private IdNode<I, V> parent;

	private Collection<IdNode<I, V>> children;

	private V value;

	public IdNode(I id, I parentId, V value) {
		this.id = id;
		this.parentId = parentId;
		this.value = value;
	}

	public IdNode(I id, I parentId) {
		this(id, parentId, null);
	}
}
