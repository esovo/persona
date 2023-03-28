class script {
  title: string;
  author: string;
  content: string;
  registrant: string;
  view_num: number;
  register_date: string;
  update_date: string;
  genre: string;
  emotion: string;

  constructor(
    title: string,
    author: string,
    content: string,
    registrant: string,
    view_num: number,
    register_date: string,
    update_date: string,
    genre: string,
    emotion: string,
  ) {
    this.title = title;
    this.author = author;
    this.content = content;
    this.register_date = register_date;
    this.registrant = registrant;
    this.view_num = view_num;
    this.update_date = update_date;
    this.genre = genre;
    this.emotion = emotion;
  }
}

export default script;
