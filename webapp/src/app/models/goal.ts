import {GoalType} from "./goal-type.enum";
import {GoalStatus} from "./goal-status.enum";
import {Topic} from "./topic";

export class Goal {
  id: Number
  text: String
  type: GoalType
  status: GoalStatus
  trialsCount: Number
  approachesCount: Number
  topics: Topic[]

  constructor(
    id: Number,
    text: String,
    type: string | GoalType = "DAILY",
    status: string | GoalStatus = "IN_PROGRESS",
    trialsCount: Number = 0,
    approachesCount: Number = 1,
    topics: Topic[] = []
  ) {
    this.id = id
    this.text = text
    this.type = GoalType[type]
    this.status = GoalStatus[status]
    this.trialsCount = trialsCount
    this.approachesCount = approachesCount
    this.topics = topics
  }

  addTopic(topic: Topic) : void {
    this.topics.push(topic)
  }

}
