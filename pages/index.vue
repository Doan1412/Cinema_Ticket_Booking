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
        <Slider/>
        <div class="mt-10">
          <span class="ml-11 text-2xl text-gray-600 dark:text-white font-medium">Now showing</span>
          <CardSlider/>
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
import authenticated from '~/middleware/authenticated.js';
export default {
  name: "IndexPage",
  components: { Sidebar, TrailerPopup, CardSlider },
  watch: {
    '$route.query': '$fetch'
  },
  data() {
    return {
    };
  },
  methods: {
  },
}

</script>
