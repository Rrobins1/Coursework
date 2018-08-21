class Node:
    def __init__(self, state, parent=None, action = None, path_cost = 0):
        self.state = state
        self.parent = parent
        self.action = action
        if parent:
            self.path_cost = parent.path_cost + path_cost
            self.depth = parent.depth + 1
        else:
            self.path_cost = path_cost
            self.depth = 0

    def __repr__(self):
        return "<Node %s>" % (self.state, )

    def nodePath(self):
        x, result = self, [self]
        while x.parent:
            result.append(x.parent)
            x = x.parent
        result.reverse()
        return result

    def expand(self, problem):
        return [Node(next, self, act, cost)
                for(next, act, cost) in problem.getSuccessors(self.state)]