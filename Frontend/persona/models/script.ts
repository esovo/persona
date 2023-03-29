class script {
  id: number;
  title: string;
  author: string;
  actor: string;
  content: string;
  viewCnt: number;
  emotion: string;
  genre: string;
  createdDate: string;
  participantCnt: number;

  constructor(
    id: number,
    title: string,
    author: string,
    actor: string,
    content: string,
    viewCnt: number,
    emotion: string,
    genre: string,
    createdDate: string,
    participantCnt: number,
  ) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.actor = actor;
    this.content = content;
    this.viewCnt = viewCnt;
    this.emotion = emotion;
    this.genre = genre;
    this.createdDate = createdDate;
    this.participantCnt = participantCnt;
  }
}

export default script;
