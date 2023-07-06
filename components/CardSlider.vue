<template>
    <div class="container mx-auto relative">
        <div class="flex items-center justify-between absolute inset-0 w-full h-full">
            <button @click="showPrev" role="button" aria-label="slide forward"
                class=" z-30  ml-10 focus:outline-none focus:bg-gray-400 focus:ring-2 focus:ring-offset-2 focus:ring-gray-400"
                id="next">
                <svg width='24px' height='24px' viewBox="0 0 8 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M7 1L1 7L7 13" stroke="white" stroke-width='2' stroke-linecap="round"
                        stroke-linejoin="round" />
                </svg>
            </button>
            <button @click="showNext" role="button" aria-label="slide forward"
                class=" z-30  mr-16 focus:outline-none focus:bg-gray-400 focus:ring-2 focus:ring-offset-2 focus:ring-gray-400"
                id="next">
                <svg width='24px' height='24px' viewBox="0 0 8 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M1 1L7 7L1 13" stroke="white" stroke-width='2' stroke-linecap="round"
                        stroke-linejoin="round" />
                </svg>
            </button>
        </div>
        <div class="flex items-center justify-center w-full h-full py-24 sm:py-8 px-4">
            <div class="slider">
                <div class="slide-ana">
                    <VueSlickCarousel v-bind="settings" ref="carousel">
                        <ul v-for="movie in listMovie" :key="movie.id">
                            <div class="px-3">

                                <div
                                    class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                                    <a href="#">
                                        <img class="p-2 rounded-lg" :src="movie.posterUrl" alt="product image" />
                                    </a>
                                    <div class="px-5 pb-5">
                                        <a href="#">
                                            <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">
                                                {{ movie.title }}</h5>
                                        </a>
                                        <div class="flex items-center mt-2.5 mb-5">
                                            <ul v-for="star in getStarRating(movie.rating)" :key="star">
                                                <svg aria-hidden="true" class="w-5 h-5 text-yellow-300" fill="currentColor"
                                                    viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                                                    <title>First star</title>
                                                    <path
                                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z">
                                                    </path>
                                                </svg>
                                            </ul>

                                            <span
                                                class="bg-blue-100 text-blue-800 text-xs font-semibold mr-2 px-2.5 py-0.5 rounded dark:bg-blue-200 dark:text-blue-800 ml-3">{{
                                                    movie.rating }}</span>
                                        </div>
                                        <div class="flex items-center justify-between">

                                            <a :href="`/movie/${movie.id}`"
                                                class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                                                Details
                                        </a>
                                            <button type="button"
                                                class="text-white bg-gradient-to-r from-red-500 via-red-600 to-red-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 shadow-lg shadow-red-500/50 dark:shadow-lg dark:shadow-red-800/80 font-medium rounded-lg text-sm px-5 py-2.5 text-center">Buy
                                                tickets</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </ul>
                    </VueSlickCarousel>

                </div>
            </div>

        </div>
    </div>
</template>

<script>
import VueSlickCarousel from "vue-slick-carousel";
import "vue-slick-carousel/dist/vue-slick-carousel.css";
export default {
    name: "MyComponent",
    components: { VueSlickCarousel },
    props: {
        listMovie: {
            type: Array,
            required: true,
        },
    },
    data() {
        return {
            settings: {
                dots: false,
                infinite: true,
                arrows: false,
                speed: 500,
                slidesToShow: 0,
                slidesToScroll: 1,
                touchThreshold: 5
            },
            windowWidth: 900,
        }
    },
    computed: {
        getStarRating() {
            return rating => {
                const roundedRating = Math.round(rating);
                return Array(roundedRating).fill('');
            };
        }
    },
    methods: {
        showNext() {
            this.$refs.carousel.next();
        },
        showPrev() {
            this.$refs.carousel.prev();
        },
    },
    mounted() {
        this.settings.slidesToShow = 3.5
    }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,400;0,700;0,900;1,400&display=swap');
@import url('https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css');

.slider {
    width: 97%;
    height: 530px;
    position: relative;
    overflow: hidden;
}

.slide-ana {
    height: 600px;
}

.slide-ana>div {
    width: 100%;
    height: 100%;
    position: absolute;
    transition: all 1s;
}

@media (min-width: 300px) and (max-width: 884px) {
    .slider {
        height: 550px;
    }
}

@media (min-width: 768px) and (max-width: 1023px) {
    .slider {
        height: 581px;
    }
}

@media (min-width: 1024px) and (max-width: 1280px) {
    .slider {
        height: 379px;
    }
}

.slider2 {
    width: 100%;
    height: 395px;
    position: relative;
    overflow: hidden;
}

.slide-ana2 {
    height: 100%;
}

.slide-ana2>div {
    width: 100%;
    height: 100%;
    position: absolute;
    transition: all 1s;
}

@media (min-width: 300px) and (max-width: 1023px) {
    .slider2 {
        height: 378px;
    }
}
</style>
