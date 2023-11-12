/** @type {import('next').NextConfig} */
const nextConfig = {
    output: 'export', // static exports, see https://nextjs.org/docs/pages/building-your-application/deploying/static-exports
    reactStrictMode: true,
    swcMinify: true,
}

module.exports = nextConfig

