package com.l.model;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * IdTree
 *
 * @author Administrator
 * @since 2025/4/8 11:55
 */
public class IdTree<I, V> {
	Map<I, IdNode<I, V>> nodeMap;

	Function<IdNode<I, V>, Boolean> isRoot;

	public IdTree() {
		nodeMap = new HashMap<>();
		isRoot = node -> node.getParentId() == null;
	}

	public void add(IdNode<I, V> node) {
		nodeMap.put(node.getId(), node);
		node.setParent(nodeMap.get(node.getParentId()));
		IdNode<I, V> parent = node.getParent();
		if (parent != null) {
			if (parent.getChildren() == null) {
				parent.setChildren(new LinkedList<>());
			}
			parent.getChildren().add(node);
		}
	}

	public IdNode<I, V> get(I id) {
		return nodeMap.get(id);
	}

	public void remove(I id) {
		nodeMap.remove(id);
	}

	public Collection<IdNode<I, V>> getRoots() {
		return nodeMap.values().parallelStream().filter(isRoot::apply).collect(Collectors.toList());
	}
}
