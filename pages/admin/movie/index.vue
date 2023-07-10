<template>
  <div>
    <div class="head">
      <Headerbar />
    </div>
    <div class="cnt">
      <div>
        <a :href="`/admin/movie/add`"
          class="text-white bg-gradient-to-br from-green-400 to-blue-600 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-green-200 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center right mr-2 mb-20">Add
          movie</a>
      </div>
      <h1 class="page-title"></h1>
      <div class="film-list">
        <div v-for="film in films" :key="film.id" class="film-item">
          <div class="film-thumbnail">
            <div class="film-options">
              <button class="options-button" @click="toggleOptions(film.id)">
                ...
              </button>
              <div v-if="film.showOptions" class="options-dropdown">
                <!-- <button class="updatebtn"></button> -->
                <div>
                  <a type="button" :href="`/admin/movie/update/${film.id}`" class="updatebtn text-center">Update</a>
                </div>
                <button @click="deleteFilm(film)" class="deletebtn ">Delete</button>
              </div>
            </div>
            <img :src="film.posterUrl" :alt="film.title" class="film-poster">
          </div>
          <h2 class="film-title">{{ film.title }}</h2>
        </div>
      </div>
    </div>
    <Notification :status="status" :object="`phim`" :action="action" :isShowNoti="showNoti" v-if="showNoti == 'Ok'">
    </Notification>
  </div>
</template>
  
<script>
import Headerbar from '~/components/Headerbar.vue';
export default {
  data() {
    return {
      films: [],
      showNoti: ``,
      status: ``,
      action: ``,
    };
  },
  async fetch() {
    try {
      await this.$axios.get(`http://localhost:8080/api/movie`).then((res) => {
        this.$data.films = res.data;
      })
      this.$data.films.forEach((f) => {
        f.showOptions = false;
      })
    } catch (error) {
      console.log(error);
    }
  },
  methods: {
    toggleOptions(filmId) {
      this.films.forEach(film => {
        if (film.id === filmId) {
          film.showOptions = !film.showOptions;
        } else {
          film.showOptions = false;
        }
      });
    },
    async deleteFilm(film) {
      // Logic to delete the film
      console.log('Delete film:', film);
      try {
        await this.$axios.delete(`http://localhost:8080/api/movie/delete/${film.id}`).then((res) => {
          const index = this.films.indexOf(film);
          if (index > -1) {
            this.films.splice(index, 1); // Xóa phần tử khỏi mảng this.films
          }
          this.status = "thành công";
          this.showNoti = "Ok";
          this.action = "Delete" ;
          setTimeout(() => {
            this.showNoti = "";
          }, 1500);
        })
      }
      catch (error) {
        console.log(error);
      }
    }
  },
  components: {
    Headerbar
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

.updatebtn {
  width: 100px;
  z-index: 10;
  color: black;
  padding: 10px;
  border-radius: 5px;
}

.deletebtn {
  width: 100px;
  z-index: 10;
  color: black;
  padding: 10px;
  border-radius: 5px;
}

.updatebtn:hover {
  background-color: #dddddd;
}

.deletebtn:hover {
  background-color: #dddddd;
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

.film-options {
  position: absolute;
  top: 10px;
  right: 10px;
  width: 30px;
  height: 30px;
  background-color: rgba(0, 0, 0, 0.7);
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border-radius: 50%;
  z-index: 1;
}

.options-dropdown {
  position: absolute;
  top: 35px;
  right: 0;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  padding: 0px;
  display: none;
  z-index: 2;
  /* Thêm dòng này */
}

.film-options:hover .options-dropdown {
  display: block;
}

.options-dropdown button {
  display: block;
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
  