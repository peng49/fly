export function buildTree(items) {
  const tree = []

  const hasChild = function(parent) {
    for (const i in items) {
      const item = items[i]
      if (item.parentId === parent.id) {
        return true
      }
    }
    return false
  }

  const getChildren = function(parent) {
    const children = []
    for (const i in items) {
      const item = items[i]
      if (item.parentId === parent.id) {
        if (hasChild(item)) {
          item.children = this.getChildren(item)
        }
        children.push(item)
      }
    }
    return children
  }

  for (const i in items) {
    const item = items[i]
    if (item.parentId !== 0) {
      continue
    }

    if (hasChild(item)) {
      item.children = getChildren(item)
    }
    tree.push(item)
  }
  return tree
}
