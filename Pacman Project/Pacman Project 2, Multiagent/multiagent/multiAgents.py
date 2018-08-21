# multiAgents.py
# --------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from util import manhattanDistance
from game import Directions
import random, util

from game import Agent

class ReflexAgent(Agent):
    """
      A reflex agent chooses an action at each choice point by examining
      its alternatives via a state evaluation function.

      The code below is provided as a guide.  You are welcome to change
      it in any way you see fit, so long as you don't touch our method
      headers.
    """


    def getAction(self, gameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {North, South, West, East, Stop}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices) # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]
        "*** YOUR CODE HERE ***"

        if ( (manhattanDistance(newPos, newGhostStates[0].getPosition())) < 2 ):
            return -100000
        score = .3*successorGameState.getScore() + 5.0/( recurseManhattan(newPos, newFood.asList() )) + 30/recurseManhattan(newPos, successorGameState.getCapsules())
        #if ( newScaredTimes[0] > manhattanDistance(newPos, newGhostStates[0].getPosition())): score + 20
        return score

def recurseManhattan(position, list):
    if len(list) == 0: return 1

    sums = []
    min = 99999
    minIndex = 0
    for xy in list:
        sums.append( (manhattanDistance(position, xy)) )

    for x in range(0, len(sums), 1):
        if sums[x] < min:
            min = sums[x]
            minIndex = x

    return min + recurseManhattan(list.pop(minIndex), list )

def scoreEvaluationFunction(currentGameState):

    """
      This default evaluation function just returns the score of the state.
      The score is the same one displayed in the Pacman GUI.

      This evaluation function is meant for use with adversarial search agents
      (not reflex agents).
    """
    return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
    """
      This class provides some common elements to all of your
      multi-agent searchers.  Any methods defined here will be available
      to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

      You *do not* need to make any changes here, but you can if you want to
      add functionality to all your adversarial search agents.  Please do not
      remove anything, however.

      Note: this is an abstract class: one that should not be instantiated.  It's
      only partially specified, and designed to be extended.  Agent (game.py)
      is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)
        self.currentDepth = 0

class MinimaxAgent(MultiAgentSearchAgent):
    """
      Your minimax agent (question 2)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action from the current gameState using self.depth
          and self.evaluationFunction.

          Here are some method calls that might be useful when implementing minimax.

          gameState.getLegalActions(agentIndex):
            Returns a list of legal actions for an agent
            agentIndex=0 means Pacman, ghosts are >= 1

          gameState.generateSuccessor(agentIndex, action):
            Returns the successor game state after an agent takes an action

          gameState.getNumAgents():
            Returns the total number of agents in the game
        """
        "*** YOUR CODE HERE ***"
        return self.minimax(gameState, 0, 0)[0]



    def minimax(self, gameState, passedIndex, passedDepth ):
        currentIndex = passedIndex
        currentDepth = passedDepth

        if (currentIndex == gameState.getNumAgents()):
            currentDepth += 1
            currentIndex = 0

        if ( len(gameState.getLegalActions(currentIndex)) == 0 or (currentDepth == self.depth)):
            return '', self.evaluationFunction(gameState)

        if (currentIndex == 0): return self.maximize( gameState, currentIndex, currentDepth )
        else: return self.minimize( gameState, currentIndex, currentDepth )


    def minimize(self, gameState, passedIndex, passedDepth):
        currentIndex = passedIndex
        currentDepth = passedDepth

        value = float('inf')
        action = ''

        for tempAction in gameState.getLegalActions(currentIndex):
            tempValue = self.minimax(gameState.generateSuccessor(currentIndex , tempAction), currentIndex + 1, currentDepth)[1]

            if (tempValue < value ):
                action = tempAction
                value = tempValue
        return action, value

    def maximize(self, gameState, passedIndex, passedDepth):
        currentIndex = passedIndex
        currentDepth = passedDepth

        value = -float('inf')
        action = ''

        for tempAction in gameState.getLegalActions(currentIndex):
            tempValue = self.minimax(gameState.generateSuccessor(currentIndex , tempAction), currentIndex + 1, currentDepth)[1]

            if (tempValue > value):
                action = tempAction
                value = tempValue
        return action, value


class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        "*** YOUR CODE HERE ***"
        return self.alphaBeta(gameState, 0, 0)[0]

    def alphaBeta(self, gameState, passedIndex, passedDepth, alpha = - float('inf'), beta = float('inf')):
        currentIndex = passedIndex
        currentDepth = passedDepth

        if (currentIndex == gameState.getNumAgents()):
            currentDepth += 1
            currentIndex = 0

        if ( len(gameState.getLegalActions(currentIndex)) == 0 or (currentDepth == self.depth)):
            return '', self.evaluationFunction(gameState)

        if (currentIndex == 0): return self.maximize( gameState, currentIndex, currentDepth, alpha, beta )
        else: return self.minimize( gameState, currentIndex, currentDepth, alpha, beta )


    def minimize(self, gameState, passedIndex, passedDepth, alpha = - float('inf'), beta = float('inf')):
        currentIndex = passedIndex
        currentDepth = passedDepth

        value = float('inf')
        action = ''

        for tempAction in gameState.getLegalActions(currentIndex):
            tempValue = self.alphaBeta(gameState.generateSuccessor(currentIndex , tempAction), currentIndex + 1, currentDepth, alpha, beta)[1]


            if (tempValue < value ):
                action = tempAction
                value = tempValue
            if (tempValue < alpha):
                return action, value
            beta = min(beta, value)
        return action, value

    def maximize(self, gameState, passedIndex, passedDepth, alpha = - float('inf'), beta = float('inf')):
        currentIndex = passedIndex
        currentDepth = passedDepth

        value = -float('inf')
        action = ''

        for tempAction in gameState.getLegalActions(currentIndex):
            tempValue = self.alphaBeta(gameState.generateSuccessor(currentIndex , tempAction), currentIndex + 1, currentDepth, alpha, beta)[1]

            if (tempValue > value):
                action = tempAction
                value = tempValue
            if (tempValue > beta):
                return action, value
            alpha = max(alpha, value)
        return action, value

class ExpectimaxAgent(MultiAgentSearchAgent):
    """
      Your expectimax agent (question 4)
    """

    def getAction(self, gameState):
        """
          Returns the expectimax action using self.depth and self.evaluationFunction

          All ghosts should be modeled as choosing uniformly at random from their
          legal moves.
        """
        "*** YOUR CODE HERE ***"
        return self.expectimax(gameState, 0, 0)[0]

    def expectimax(self, gameState, passedIndex, passedDepth ):
        currentIndex = passedIndex
        currentDepth = passedDepth

        if (currentIndex == gameState.getNumAgents()):
            currentDepth += 1
            currentIndex = 0

        if ( len(gameState.getLegalActions(currentIndex)) == 0 or (currentDepth == self.depth)):
            return '', self.evaluationFunction(gameState)

        if (currentIndex == 0): return self.maximize( gameState, currentIndex, currentDepth )
        else: return self.minimize( gameState, currentIndex, currentDepth )


    def minimize(self, gameState, passedIndex, passedDepth):
        currentIndex = passedIndex
        currentDepth = passedDepth
        value = 0
        action =''
        count = 0

        for tempAction in gameState.getLegalActions(currentIndex):
            tempValue = self.expectimax(gameState.generateSuccessor(currentIndex, tempAction), currentIndex + 1, currentDepth)[1]

            value += tempValue
            count += 1

        value = float(value) / float(count)
        return action, value


    def maximize(self, gameState, passedIndex, passedDepth):
        currentIndex = passedIndex
        currentDepth = passedDepth

        value = -float('inf')
        action = ''

        for tempAction in gameState.getLegalActions(currentIndex):
            tempValue = self.expectimax(gameState.generateSuccessor(currentIndex , tempAction), currentIndex + 1, currentDepth)[1]

            if (tempValue > value):
                action = tempAction
                value = tempValue
        return action, value

def betterEvaluationFunction(currentGameState):
    """
      Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
      evaluation function (question 5).

      DESCRIPTION: <write something here so we know what you did>
    """
    "*** YOUR CODE HERE ***"
    successorGameState = currentGameState
    newPos = successorGameState.getPacmanPosition()
    newFood = successorGameState.getFood()
    newGhostStates = successorGameState.getGhostStates()
    newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]
    "*** YOUR CODE HERE ***"

    if ((manhattanDistance(newPos, newGhostStates[0].getPosition())) < 2):
        return -100000
    score = .3 * successorGameState.getScore() + 5.0 / (
        recurseManhattan(newPos, newFood.asList())) + 30 / recurseManhattan(newPos, successorGameState.getCapsules())
    # if ( newScaredTimes[0] > manhattanDistance(newPos, newGhostStates[0].getPosition())): score + 20
    return score

# Abbreviation
better = betterEvaluationFunction

