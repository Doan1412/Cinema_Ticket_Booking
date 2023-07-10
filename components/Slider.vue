<template>
    <div class="container">
        <div id="animation-carousel" class="relative w-full" data-carousel="static">
            <div class="relative hg overflow-hidden rounded-2xl md:h-96">
                <ul v-for="movie in movies" :key="movie.id">
                    <div class="hidden duration-300 ease-linear hg" data-carousel-item>
                        <img :src="movie.bannerUrl"
                            class="absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2" alt="..." />
                        <div class="absolute inset-x-[15%] bottom-5 hidden mb-10 text-white md:block z-50">
                            <h5 class="text-6xl font-medium text-white ">{{ movie.title }}</h5>
                            <div class="pt-2 pl-1 w-500">
                                <svg class="w-5 h-5 inline-block" fill="none" stroke="currentColor" viewBox="0 0 50 50"
                                    xmlns="http://www.w3.org/2000/svg">
                                    <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                                    <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g>
                                    <g id="SVGRepo_iconCarrier">
                                        <path
                                            d="M16.0005 0L21.4392 9.27275L32.0005 11.5439L24.8005 19.5459L25.889 30.2222L16.0005 25.895L6.11194 30.2222L7.20049 19.5459L0.000488281 11.5439L10.5618 9.27275L16.0005 0Z"
                                            fill="#FFCB45"></path>
                                    </g>
                                </svg>
                                <p class="text-white font-normal text-lg pl-2 inline-block pb-1">{{ movie.rating }} </p>
                                <p class="text-gray-100 font-normal text-md pl-2 inline-block pb-1">| 1055</p>
                                <p class="text-gray-150 font-normal text-sm pl-10 inline-block pb-1">{{ movie.duartionMin }}
                                    m
                                    &bull; {{ movie.genre }} &bull; {{ movie.releaseDate }}</p>
                                <p class="text-white font-normal text-lg pt-5 content">{{ movie.description }}</p>
                            </div>
                            <div class="pt-5">
                                <a type="button" :href="`/ticket/movie/${movie.id}`"
                                    class="text-white bg-gradient-to-r from-red-500 via-red-600 to-red-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 shadow-lg shadow-red-500/50 dark:shadow-lg dark:shadow-red-800/80 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2 mb-2">
                                    Buy tickets
                                </a>
                                <div class="inline-block">
                                    <TrailerPopup :url="movie.trailerUrl" />
                                </div>
                            </div>
                        </div>
                    </div>
                </ul>
            </div>
            <button type="button"
                class="absolute top-0 left-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
                data-carousel-prev>
                <span
                    class="inline-flex items-center justify-center w-8 h-8 rounded-full sm:w-10 sm:h-10 bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
                    <svg aria-hidden="true" class="w-5 h-5 text-white sm:w-6 sm:h-6 dark:text-gray-800" fill="none"
                        stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path>
                    </svg>
                    <span class="sr-only">Previous</span>
                </span>
            </button>
            <button type="button"
                class="absolute top-0 right-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
                data-carousel-next>
                <span
                    class="inline-flex items-center justify-center w-8 h-8 rounded-full sm:w-10 sm:h-10 bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
                    <svg aria-hidden="true" class="w-5 h-5 text-white sm:w-6 sm:h-6 dark:text-gray-800" fill="none"
                        stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                    </svg>
                    <span class="sr-only">Next</span>
                </span>
            </button>
        </div>
    </div>
</template>
<style>
.container {
    max-width: 1060px !important;
    margin-left: 40px;
    /* opacity: 0.85; */
}

.hg {
    height: 600px;
}

.content {
    width: 500px;
    word-wrap: normal;
}
</style>
<script>
import { onMounted } from 'vue';
import { Modal } from 'flowbite';
import TrailerPopup from './TrailerPopup.vue';
export default {
    components: {
        TrailerPopup,
    },
    props: {
        movies: {
            type: Array,
            required: true,
        },
    },
    method: {
        check() {
            console.log(this.movies);
        },
        getReleaseYear(releaseDate) {
            if (releaseDate) {
                const date = new Date(releaseDate);
                return date.getFullYear();
            }
            return '';
        },
        onMounted() {
            // setup available elements
            const $buttonElement = document.querySelector('#button');
            const $modalElement = document.querySelector('#modal');
            const $closeButton = document.querySelector('#closeButton');

            // set modal options
            const modalOptions = {
                backdropClasses: 'bg-gray-900 bg-opacity-50 dark:bg-opacity-80 fixed inset-0 z-40'
            }

            // create a new modal instance
            if ($modalElement) {
                const modal = new Modal($modalElement, modalOptions);

                // set event listeners for the button to show the modal
                $buttonElement.addEventListener('click', () => modal.toggle());
                $closeButton.addEventListener('click', () => modal.hide());
            }
        }
    }
}


</script>