import Link from 'next/link';

export default function Header() {
  return (
    <nav>
      <Link href="/">PERSONA</Link>
      <Link href="/practice">연기연습</Link>
      <Link href="/community">커뮤니티</Link>
      <Link href="/storage">보관함</Link>
      <Link href="/bookmark">북마크</Link>
    </nav>
  );
}
