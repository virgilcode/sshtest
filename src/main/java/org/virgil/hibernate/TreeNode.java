package org.virgil.hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Virgil on 2017/5/8.
 */
@Entity
@Table(name = "tree_node")
public class TreeNode {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "NAME", length = 20)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
    private TreeNode parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<TreeNode> children = new LinkedHashSet<TreeNode>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public Set<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(Set<TreeNode> children) {
        this.children = children;
    }

    public TreeNode() {
    }

    public TreeNode(String name) {
        this.name = name;
    }
}
