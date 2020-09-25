import {GoalType} from "./goal-type.enum";
import {GoalStatus} from "./goal-status.enum";
import {Topic} from "./topic";

export class Goal {
  id: Number
  text: String
  type: GoalType
  status: GoalStatus
  trialsCount: number
  approachesCount: number
  topics: Topic[]

  constructor(
    id: Number = null,
    text: String = null,
    type: GoalType = GoalType.DAILY,
    status: GoalStatus = GoalStatus.IN_PROGRESS,
    trialsCount: number = 1,
    approachesCount: number = 0,
    topics: Topic[] = []
  ) {
    this.id = id
    this.text = text
    this.type = type
    this.status = status
    this.trialsCount = trialsCount
    this.approachesCount = approachesCount
    this.topics = topics
  }

  isDone() : boolean {
    return this.status === GoalStatus.DONE
  }

  increaseTrial() {
    this.approachesCount ++
    if (this.trialsCount === this.approachesCount) {
      this.status = GoalStatus.DONE
    }
  }

  addTopic(topic: Topic) : void {
    this.topics.push(topic)
  }

}
