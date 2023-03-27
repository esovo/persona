import dynamic from 'next/dynamic';
import { useState } from 'react';
import 'react-quill/dist/quill.snow.css';
import style from '@/styles/PostModal.module.scss';

const QuillWrapper = dynamic(() => import('react-quill'), {
  ssr: false,
  loading: () => <p>Loading ...</p>,
});

const modules = {
  toolbar: [
    [{ header: '1' }, { header: '2' }, { font: [] }],
    [{ size: [] }],
    ['bold', 'italic', 'underline', 'strike', 'blockquote'],
    [{ list: 'ordered' }, { list: 'bullet' }, { indent: '-1' }, { indent: '+1' }],
    ['link', 'image', 'video'],
    ['clean'],
  ],
  clipboard: {
    // toggle to add extra line breaks when pasting HTML:
    matchVisual: false,
  },
};
/*

Quill editor formats
See https://quilljs.com/docs/formats/
*/
const formats = [
  'header',
  'font',
  'size',
  'bold',
  'italic',
  'underline',
  'strike',
  'blockquote',
  'list',
  'bullet',
  'indent',
  'link',
  'image',
  'video',
];
export default function Home() {
  const [text, setText] = useState<string>('');

  const handleEditorChange = (value: string) => {
    setText(value);
    console.log(text);
  };

  return (
    <QuillWrapper
      className={style.quill}
      modules={modules}
      formats={formats}
      theme="snow"
      onChange={handleEditorChange}
    />
  );
}
