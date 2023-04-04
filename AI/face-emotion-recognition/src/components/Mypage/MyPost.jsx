

import style from './MyPost.module.scss';

import Post from '../CommunityPage/Post';

export default function MyPost() {

    return (
        <div className={style.outline}>
            <div className={style.container}>
                <Post />


            </div>
        </div>

    );
}