/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './**/*.html',
    './**/*.js',
  ],
  theme: {
    extend: {
      colors: {
        'custom-green': '#405743',
        'custom-red-20': 'rgba(56, 21, 21, 0.20)',
        'custom-brown-25': 'rgba(80, 65, 62, 0.25)',
        'custom-beige': '#FFEBCA',
        'custom-orange': '#C2823A',
        'custom-brown': '#50413E',
        'custom-brown-20': 'rgba(80, 65, 62, 0.20)',
        'custom-orange-20': 'rgba(194, 130, 58, 0.20)',
      },
    },
  },
  plugins: [],
}