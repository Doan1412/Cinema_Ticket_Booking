<template>
<div>
    <div class="pl-10">
      <label class="relative inline-block">
        <span class="absolute inset-y-0 left-0 flex items-center pl-2">
          <svg class="h-5 w-5 fill-slate-300 mt-1 ml-1" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"
            fill="none">
            <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
            <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g>
            <g id="SVGRepo_iconCarrier">
              <path fill="#000000" fill-rule="evenodd"
                d="M4 9a5 5 0 1110 0A5 5 0 014 9zm5-7a7 7 0 104.2 12.6.999.999 0 00.093.107l3 3a1 1 0 001.414-1.414l-3-3a.999.999 0 00-.107-.093A7 7 0 009 2z">
              </path>
            </g>
          </svg>

        </span>
        <input type="text" name="search"
          class="mt-1 pl-10 px-3 py-2 bg-gray-50 border-2 border-gray-400 rounded-full w-80 h-10 hover:border-cyan-400 placeholder-slate-400 focus:outline-none focus:border-cyan-400 focus:ring-cyan-400 focus:ring-1"
          placeholder=" " />
      </label>
      
      <div class="float-right inline-block mr-10">
        <button class="inline-block">
          <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="h-7 w-7 mb-2"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path d="M9 17.5V18.5C9 20.1569 10.3431 21 12 21C13.6569 21 15 20.1569 15 18.5V17.5M5.99999 8.5C5.99999 5.18629 8.68628 3.5 12 3.5C15.3137 3.5 18 5.18629 18 8.5C18 10.4392 18.705 12.6133 19.4316 14.3389C20.0348 15.7717 19.0222 17.5 17.4676 17.5H6.53237C4.97778 17.5 3.96518 15.7717 4.56842 14.3389C5.29493 12.6133 5.99999 10.4392 5.99999 8.5Z" stroke="#000000" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path> </g></svg>
        </button>
        <button class="inline-block mx-5 bg-slate-100 pl-2 pr-4 pt-2 pb-1.5 rounded-lg">
          <div class="inline-block">
            <img src="https://bazaarvietnam.vn/wp-content/uploads/2022/10/harper-bazaar-phim-cua-huong-ham-chi-9.jpeg" alt="" class="w-10 h-10 mr-2 border rounded-full flex ring-2 ring-green-400 dark:ring-green-600">

          </div>
          <div class = "inline-block">
            <div class = "font-bold">Crystal Gia</div>
            <span class="bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded dark:bg-green-900 dark:text-green-300">Watcher</span>
            <!-- <div class="text-sm text-teal-500">Watcher</div> -->
          </div>
        </button>
      </div>
    </div>
  </div>
</template>