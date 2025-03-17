package com.l.entity;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * BaseTree
 *
 * @author luliangyu
 * @date 2021-11-30 09:38
 */
@Data
public abstract class BaseTreeNode<T extends BaseTreeNode<T, I>, I extends Serializable> implements Serializable {
	private static final long serialVersionUID = 9219181527224300096L;
	/**
	 * 节点id
	 */
	private I id;
	/**
	 * 父节点id
	 */
	private I fatherId;
	/**
	 * 父节点
	 */
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private T father;

	/**
	 * 子节点
	 */
	@EqualsAndHashCode.Exclude
	private Collection<T> child;

	/**
	 * 根据已有的节点集合 根据 id 和 fatherId 构建出 树
	 *
	 * @param nodes 节点集合
	 * @param <T>   节点类型
	 * @return 根节点集合
	 */
	public static <T extends BaseTreeNode<T, I>, I extends Serializable> Set<T> buildTree(Collection<T> nodes) {
		if (CollUtil.isEmpty(nodes)) {
			return new LinkedHashSet<>();
		}
		Map<I, List<T>> map = nodes.stream().collect(Collectors.groupingBy(T::getId));
		// 父节点为key 同时 排除root节点作为子节点的可能性
		Map<I, List<T>> childMap = nodes.stream().filter(item -> !item.isRoot()).collect(Collectors.groupingBy(T::getFatherId));
		for (Map.Entry<I, List<T>> item : map.entrySet()) {
			BaseTreeNode<T, I> node = item.getValue().get(0);
			// 子节点
			List<T> child = childMap.get(node.getId());
			// 父节点
			if (node.isRoot()) {
				node.setFather(null);
			} else {
				node.setFather(Optional.ofNullable(map.get(node.getFatherId())).map(opt -> opt.get(0)).orElse(null));
			}
			node.setChild(child);
		}
		return nodes.stream().filter(item -> item.getFather() == null).collect(Collectors.toCollection(LinkedHashSet::new));
	}

	/**
	 * 根据已有的节点集合 根据 id 和 fatherId 构建出 树
	 *
	 * @param nodes 节点集合
	 * @param <T>   节点类型
	 * @return 根节点集合
	 */
	public static <T extends BaseTreeNode<T, I>, I extends Serializable> T buildOneTree(Collection<T> nodes) {
		Set<T> data = buildTree(nodes);
		if (data.isEmpty()) {
			return null;
		}
		if (data.size() == 1) {
			return data.iterator().next();
		} else {
			throw new RuntimeException("构建出多个根节点");
		}
	}

	/**
	 * 当前节点是否为根节点
	 *
	 * @return -true 根节点
	 */
	public boolean isRoot() {
		return Objects.equals(this.getFatherId(), this.treeRootId());
	}

	/**
	 * 创建id
	 *
	 * @return id
	 */
	protected abstract I createId();

	/**
	 * 根节点id
	 *
	 * @return rootId
	 */
	protected abstract I treeRootId();

	/**
	 * 根据当前节点向下平铺整棵树
	 *
	 * @return 平铺后的set集合
	 */
	public Set<T> tiledByNode() {
		return tiledByNode(Collections.singletonList((T) this));
	}

	/**
	 * 根据节点列表平铺节点数据(包括自己)
	 *
	 * @param nodes 节点集合
	 * @return 平铺后的节点集合
	 */
	private Set<T> tiledByNode(Collection<T> nodes) {
		LinkedHashSet<T> result = new LinkedHashSet<>();
		if (nodes == null || nodes.isEmpty()) {
			return result;
		}
		result.addAll(nodes);
		for (T item : nodes) {
			result.addAll(tiledByNode(item.getChild()));
		}
		return result;
	}

	/**
	 * 根据当前节点 重新生成id信息
	 */
	public void batchSetTreeNodeId() {
		T rootNode = this.getRootNode();
		batchSetNodeId(Collections.singletonList(rootNode));
	}

	/**
	 * 向下设置 id
	 *
	 * @param root 根节点集合
	 */
	private void batchSetNodeId(Collection<T> root) {
		if (CollUtil.isEmpty(root)) {
			throw new RuntimeException("节点为空");
		}
		for (BaseTreeNode<T, I> item : root) {
			// 父节点存在 设置父节点 父节点不存在 设置父级id为 根节点id
			item.setFatherId(Optional.ofNullable(item.getFather()).map(BaseTreeNode::getId).orElse(item.treeRootId()));
			// 生成当前节点id
			item.setId(item.createId());
			if (!CollUtil.isEmpty(item.getChild())) {
				// 递归设置子节点id
				batchSetNodeId(item.getChild());
			}
		}
	}

	/**
	 * 根据当前节点获取根节点
	 *
	 * @return 根节点
	 */
	public T getRootNode() {
		if (getFather() == null) {
			return (T) this;
		}
		return getFather().getRootNode();
	}

	/**
	 * 获取树的等级
	 *
	 * @return 等级 1~n
	 */
	public int getNodeLevel() {
		if (getFather() == null) {
			return 1;
		} else {
			return getFather().getNodeLevel() + 1;
		}
	}

	/**
	 * 获得某个级别的节点
	 *
	 * @param level 级别
	 * @return 级别对应的节点
	 */
	public Set<T> findNodeByLevel(int level) {
		return getRootNode().tiledByNode().stream()
			.filter(item -> item.getNodeLevel() == level)
			.collect(Collectors.toSet());
	}

	/**
	 * 获取所有叶节点(没有子)
	 *
	 * @return 叶节点set集合
	 */
	public Set<T> getAllLeafNode() {
		return getRootNode().tiledByNode().parallelStream()
			.filter(item -> CollUtil.isEmpty(item.getChild()))
			.collect(Collectors.toSet());
	}

	/**
	 * 节点的全路径id
	 *
	 * @return 全路径id
	 */
	public String getFullId() {
		if (getFather() == null) {
			return String.valueOf(this.getId());
		} else {
			return this.getFather().getFullId() + "/" + this.getId();
		}
	}

	/**
	 * 根据id获取指定节点
	 *
	 * @param id 节点id
	 * @return 找不到 返回 null
	 */
	public T findNodeById(I id) {
		Set<T> set = this.getRootNode().tiledByNode().parallelStream().filter(item -> Objects.equals(item.getId(), id)).collect(Collectors.toSet());
		if (set.isEmpty()) {
			return null;
		}
		return set.iterator().next();
	}
}
