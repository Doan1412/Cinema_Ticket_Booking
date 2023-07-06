<template>
    <div>
        <div class="head">
            <Headerbar />
        </div>
        <div class="flex-container">
            <div>
                <img class="poster" :src="movie.posterUrl">
            </div>
            <div class="info">
                <p class="text-5xl text-gray-900 dark:text-white pb-3">{{ movie.title }}</p>
                <div class="flex items-center mt-2.5 mb-7">
                    <ul v-for="star in getStarRating(movie.rating)" :key="star">
                        <svg aria-hidden="true" class="w-6 h-6 text-yellow-300" fill="currentColor" viewBox="0 0 20 20"
                            xmlns="http://www.w3.org/2000/svg">
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
                <span class="text-2xl font-medium text-gray-900 dark:text-white pb-7">Genre: </span>
                <span class="text-2xl font-normal text-gray-900 dark:text-white pb-7"> {{ movie.genre }}</span>
                <br>
                <span class="text-2xl font-medium text-gray-900 dark:text-white pb-7">Actor: </span>
                <span class="text-2xl font-normal text-gray-900 dark:text-white pb-7"> {{ movie.actorList }}</span>
                <br>
                <span class="text-2xl font-medium text-gray-900 dark:text-white pb-7">Release day: </span>
                <span class="text-2xl font-normal text-gray-900 dark:text-white pb-7">{{ movie.releaseDate }}</span>
                <br>
                <span class="text-2xl font-medium text-gray-900 dark:text-white pb-7">Country: </span>
                <span class="text-2xl font-normal text-gray-900 dark:text-white pb-7"> {{ movie.country }}</span>
                <br>
                <span class="text-2xl font-medium text-gray-900 dark:text-white pb-7">Duration: </span>
                <span class="text-2xl font-normal text-gray-900 dark:text-white pb-7"> {{ movie.duartionMin }}
                    minutes</span>
                <br>
                <span class="text-2xl font-normal text-gray-900 dark:text-white"> {{ movie.description }}</span>
                <div class="cnt mt-5">
                    <div class='container'>
                        <a class='playBut' @click="playTrailer">


                            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                                xmlns:a="http://ns.adobe.com/AdobeSVGViewerExtensions/3.0/" x="0px" y="0px" width="50px"
                                height="50px" viewBox="0 0 213.7 213.7" enable-background="new 0 0 213.7 213.7"
                                xml:space="preserve">

                                <polygon class='triangle' id="XMLID_18_" fill="none" stroke-width="7" stroke-linecap="round"
                                    stroke-linejoin="round" stroke-miterlimit="10" points="
              73.5,62.5 148.5,105.8 73.5,149.1 " />

                                <circle class='circle' id="XMLID_17_" fill="none" stroke-width="7" stroke-linecap="round"
                                    stroke-linejoin="round" stroke-miterlimit="10" cx="106.8" cy="106.8" r="103.3" />
                            </svg>
                            <button class="btn btn-trailer"> Watch trailer</button>
                        </a>
                    </div>
                    <div>
                        <button class="button">
                            <span class="button__text">
                                <span>Buy tickets</span>
                            </span>
                            <svg class="button__svg" role="presentational" viewBox="0 0 600 600">
                                <defs>
                                    <clipPath id="myClip">
                                        <rect x="0" y="0" width="100%" height="50%" />
                                    </clipPath>
                                </defs>
                                <g clip-path="url(#myClip)">
                                    <g id="money">
                                        <path
                                            d="M441.9,116.54h-162c-4.66,0-8.49,4.34-8.62,9.83l.85,278.17,178.37,2V126.37C450.38,120.89,446.56,116.52,441.9,116.54Z"
                                            fill="#699e64" stroke="#323c44" stroke-miterlimit="10" stroke-width="14" />
                                        <path
                                            d="M424.73,165.49c-10-2.53-17.38-12-17.68-24H316.44c-.09,11.58-7,21.53-16.62,23.94-3.24.92-5.54,4.29-5.62,8.21V376.54H430.1V173.71C430.15,169.83,427.93,166.43,424.73,165.49Z"
                                            fill="#699e64" stroke="#323c44" stroke-miterlimit="10" stroke-width="14" />
                                    </g>
                                    <g id="creditcard">
                                        <path
                                            d="M372.12,181.59H210.9c-4.64,0-8.45,4.34-8.58,9.83l.85,278.17,177.49,2V191.42C380.55,185.94,376.75,181.57,372.12,181.59Z"
                                            fill="#a76fe2" stroke="#323c44" stroke-miterlimit="10" stroke-width="14" />
                                        <path
                                            d="M347.55,261.85H332.22c-3.73,0-6.76-3.58-6.76-8v-35.2c0-4.42,3-8,6.76-8h15.33c3.73,0,6.76,3.58,6.76,8v35.2C354.31,258.27,351.28,261.85,347.55,261.85Z"
                                            fill="#ffdc67" />
                                        <path d="M249.73,183.76h28.85v274.8H249.73Z" fill="#323c44" />
                                    </g>
                                </g>
                                <g id="wallet">
                                    <path
                                        d="M478,288.23h-337A28.93,28.93,0,0,0,112,317.14V546.2a29,29,0,0,0,28.94,28.95H478a29,29,0,0,0,28.95-28.94h0v-229A29,29,0,0,0,478,288.23Z"
                                        fill="#a4bdc1" stroke="#323c44" stroke-miterlimit="10" stroke-width="14" />
                                    <path
                                        d="M512.83,382.71H416.71a28.93,28.93,0,0,0-28.95,28.94h0V467.8a29,29,0,0,0,28.95,28.95h96.12a19.31,19.31,0,0,0,19.3-19.3V402a19.3,19.3,0,0,0-19.3-19.3Z"
                                        fill="#a4bdc1" stroke="#323c44" stroke-miterlimit="10" stroke-width="14" />
                                    <path d="M451.46,435.79v7.88a14.48,14.48,0,1,1-29,0v-7.9a14.48,14.48,0,0,1,29,0Z"
                                        fill="#a4bdc1" stroke="#323c44" stroke-miterlimit="10" stroke-width="14" />
                                    <path
                                        d="M147.87,541.93V320.84c-.05-13.2,8.25-21.51,21.62-24.27a42.71,42.71,0,0,1,7.14-1.32l-29.36-.63a67.77,67.77,0,0,0-9.13.45c-13.37,2.75-20.32,12.57-20.27,25.77l.38,221.24c-1.57,15.44,8.15,27.08,25.34,26.1l33-.19c-15.9,0-28.78-10.58-28.76-25.93Z"
                                        fill="#7b8f91" />
                                    <path d="M148.16,343.22a6,6,0,0,0-6,6v92a6,6,0,0,0,12,0v-92A6,6,0,0,0,148.16,343.22Z"
                                        fill="#323c44" />
                                </g>

                            </svg>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div v-if="isDisplaying" class="trailer-vid">
            <iframe width="900" height="600" :src="movie.trailerUrl">
            </iframe>
            <!-- <video :src="movie.trailerUrl" controls width="900" height="600"></video> -->
        </div>
    </div>
</template>

<script>
import Headerbar from '~/components/Headerbar.vue';
export default {
    data() {
        return {
            id: null,
            movie: null,
            isDisplaying: false,
        };
    },
    async fetch() {
        try {
            this.id = this.$route.params.id;
            let url = `http://localhost:8080/api/movie/` + this.id;
            await this.$axios.get(url).then((response) => {
                this.$data.movie = response.data;
            });
        }
        catch (error) {
            console.log(error);
        }
    },
    methods: {
        playTrailer() {
            this.isDisplaying = true; // Set isDisplaying to true to show the trailer video
        },
    },
    computed: {
        getStarRating() {
            return rating => {
                const roundedRating = Math.round(rating);
                return Array(roundedRating).fill("");
            };
        }
    },
    components: { Headerbar, Headerbar }
}
</script>
<style>
.head{
    margin-left: 250px;
    height: 50px;
    width: 1000px;
    padding-top: 50px;
    padding-bottom: 50px;
}
.poster {
    width: 300px;
    border-radius: 3%;
    margin-right: 80px;
}

.flex-container {
    display: flex;
    width: 1000px;
    margin: 100px 300px;
    height: 480px;
    /* background-color: rgba(0, 0, 0, 0.8); */
}

.info {
    /* margin-top: 30px; */
    /* color: aliceblue; */
    width: 500px;
}

h1 {
    margin-bottom: 30px;
}

/* .video-play-button{
    margin-top: 15px;
    border: 1px solid white;
    display: block;
    width: 50px;
    height: 50px;
    border-radius: 50%;
} */
.container {
    text-align: center;
    /* width: px; */
    margin-left: 0px !important;
    margin-top: 25px;
    height: 60px;
    display: flex;
    width: 200px;
}

.cnt {
    display: flex;
    justify-content: space-between;
}

.circle {
    stroke: #ff0000;
    stroke-dasharray: 650;
    stroke-dashoffset: 650;
    -webkit-transition: all 0.5s ease-in-out;
    opacity: 0.3;
    display: flex;
}

.playBut {
    /* border: 1px solid red;
   */
    -webkit-transition: all 0.5s ease;
    display: flex;
}

.playBut .triangle {
    -webkit-transition: all 0.7s ease-in-out;
    stroke-dasharray: 240;
    stroke-dashoffset: 480;
    stroke: #ff0000;
    transform: translateY(0);
}

.playBut:hover .triangle {
    stroke-dashoffset: 0;
    opacity: 1;
    stroke: #ff0000;
    animation: nudge 0.7s ease-in-out;
}

@keyframes nudge {
    0% {
        transform: translateX(0);
    }

    30% {
        transform: translateX(-5px);
    }

    50% {
        transform: translateX(5px);
    }

    70% {
        transform: translateX(-2px);
    }

    100% {
        transform: translateX(0);
    }
}

.playBut:hover .circle {
    stroke-dashoffset: 0;
    opacity: 1;
}

.btn-trailer {
    margin-left: 5px;
    margin-bottom: 10px;
    /* background-color: #1f2029; */
    color: #ff0000;
    font-size: 20px;
}

.trailer-vid {
    margin-left: 300px;
}

@media (hover: hover) {
    #creditcard {
        /*  set start position */
        transform: translateY(110px);
        transition: transform 0.1s ease-in-out;
        /*  set transition for mouse enter & exit */
    }

    #money {
        /*  set start position */
        transform: translateY(180px);
        transition: transform 0.1s ease-in-out;
        /*  set transition for mouse enter & exit */
    }

    button:hover #creditcard {
        transform: translateY(0px);
        transition: transform 0.2s ease-in-out;
        /*  overide transition for mouse enter */
    }

    button:hover #money {
        transform: translateY(0px);
        transition: transform 0.3s ease-in-out;
        /*  overide transition for mouse enter */
    }
}

@keyframes bounce {
    0% {
        transform: translateY(0);
    }

    50% {
        transform: translateY(-0.25rem);
    }

    100% {
        transform: translateY(0);
    }
}
.button:hover .button__text span {
    transform: translateY(-0.25rem);
    transition: transform .2s ease-in-out;
}

/* styling */

.button {
    display: block;
    height: 60px;
    margin-top: 10px;
    margin-left: 10px;
    margin-bottom: 10px;
    outline: none;
    border: solid 1px #0e7f93;
    padding: 1rem 90px 1rem 2rem;
    position: relative;
    border-radius: 40px;
    letter-spacing: 0.7px;
    /* background-color: #1f2029; */
    color: #0e7f93;
    font-size: 21px;
    cursor: pointer;
    box-shadow: rgba(0, 9, 61, 0.2) 0px 4px 8px 0px;
}

.button:active {
    transform: translateY(1px);
}

.button__svg {
    position: absolute;
    overflow: visible;
    bottom: 6px;
    right: 0.2rem;
    height: 140%;
}

/* CSS */
.button-64 {
    align-items: center;
    background-image: linear-gradient(144deg, #AF40FF, #5B42F3 50%, #00DDEB);
    border: 0;
    border-radius: 8px;
    box-shadow: rgba(151, 65, 252, 0.2) 0 15px 30px -5px;
    box-sizing: border-box;
    color: #FFFFFF;
    display: flex;
    font-family: Phantomsans, sans-serif;
    font-size: 20px;
    justify-content: center;
    line-height: 1em;
    max-width: 100%;
    min-width: 140px;
    padding: 3px;
    text-decoration: none;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    white-space: nowrap;
    cursor: pointer;
}

.button-64:active,
.button-64:hover {
    outline: 0;
}

.button-64 span {
    background-color: rgb(5, 6, 45);
    padding: 16px 24px;
    border-radius: 6px;
    width: 100%;
    height: 100%;
    transition: 300ms;
}

.button-64:hover span {
    background: none;
}

@media (min-width: 768px) {
    .button-64 {
        font-size: 24px;
        min-width: 196px;
    }
}
</style>