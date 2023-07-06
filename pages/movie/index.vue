<template>
    <div>
        <div class="head">
            <Headerbar />
        </div>
        <div class="cnt">
            <h1 class="page-title"></h1>
            <div class="film-list">
                <a v-for="film in films" :key="film.id" :href="`/movie/${film.id}`" class="film-link">
                    <div class="film-item">
                        <div class="film-thumbnail">
                            <img :src="film.posterUrl" :alt="film.title" class="film-poster">
                        </div>
                        <h2 class="film-title">{{ film.title }}</h2>
                    </div>
                </a>
            </div>
        </div>
    </div>
</template>
  
<script>
import Headerbar from '~/components/Headerbar.vue';
export default {
    data() {
        return {
            films: []
        };
    },
    async fetch() {
        try {
            await this.$axios.get(`http://localhost:8080/api/movie`).then((res) => {
                this.$data.films = res.data;
            })
        } catch (error) {
            console.log(error);
        }
    }
};
</script>
  
<style>
.head {
    margin-left: 220px;
    height: 50px;
    width: 1000px;
    padding-top: 50px;
    padding-bottom: 100px;
}

.cnt {
    width: 950px;
    margin-left: 250px;
}

.page-title {
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 2rem;
  padding-left: 400px;
  text-transform: uppercase;
  /* Additional styles */
}

.film-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.film-item {
    width: 200px;
    margin-bottom: 20px;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.film-thumbnail {
    position: relative;
    overflow: hidden;
    padding-top: 150%;
}

.film-poster {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.3s ease;
}

.film-title {
    margin-top: 10px;
    text-align: center;
    font-size: 16px;
    font-weight: bold;
    color: #333;
    padding: 10px;
    background-color: #f5f5f5;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
}
</style>