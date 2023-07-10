<template>
  <div class="2xl:container md:mx-auto flex flex-row bg-gray-50">
    <div class="basis-1/5">
      <div class="md:container md:mx-auto w-70">
        <Sidebar />
      </div>
    </div>
    <div class="basis-5/6 mt-10">
      <Headerbar />
      <div class="mt-10 ml-500">
        <!-- <div class="img-wrapper">
          <img src="https://i0.wp.com/writertherapy.com/wp-content/uploads/2020/03/Joker-2019-Movie-banner-spread.jpg?fit=1024%2C576&ssl=1" alt="" class="flex rounded-2xl align-middle opacity-90">
        </div> -->
        <Slider :movies="this.$data.listMovieAd"/>
        <div class="mt-10">
          <span class="ml-11 text-2xl text-gray-600 dark:text-white font-medium">Now showing</span>
          <CardSlider :listMovie="listNowShow"/>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import Sidebar from '~/components/Sidebar.vue';
import Headerbar from '~/components/Headerbar.vue';
import TrailerPopup from '~/components/TrailerPopup.vue';
import CardSlider from '~/components/CardSlider.vue';
import Slider from '~/components/Slider.vue';
export default {
  name: "IndexPage",
  components: { Sidebar, TrailerPopup, CardSlider },
  data() {
    return {
      listMovieAd: [],
      listNowShow: [],
    };
  },
  methods: {
  },
  async fetch() {
    try {
      let url = `http://localhost:8080/api/movie/isAdvertise`;
      await this.$axios.get(url).then((response) => {
        this.$data.listMovieAd = response.data;
      })
      await this.$axios.get(`http://localhost:8080/api/movie`).then((res) =>{
        this.$data.listNowShow = res.data;
      })
      // const response = await this.$axios.get(url);
      // this.$data.listMovieAd = response.data;
    } catch (error) {
      console.log(error);
    }
  }
}

</script>
