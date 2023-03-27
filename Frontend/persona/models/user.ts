class user {
  id: string;
  nickname: string;
  socialAuth: string;

  constructor(userid: string, usernick: string, auth: string) {
    this.id = userid;
    this.nickname = usernick;
    this.socialAuth = auth;
  }
}

export default user;
