import React from "react";
import "../Post/Post.scss" ;


function Post(props) {

    const{title,text} = props;

    return(
        <div className="postContainer">
            {title}
            {text}
        </div>
    )
   



}

export default Post;