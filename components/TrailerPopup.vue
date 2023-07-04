<template>
    <div class="ctn">
      <!-- Modal toggle button -->
      <button type="button" @click="toggleModal"
        class="py-2.5 px-5 mr-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700">
        Trailer
      </button>
  
      <!-- Modal -->
      <div v-show="isModalOpen" class="fixed inset-0 z-50 flex items-center justify-center">
        <div class="relative w-full max-w-6xl">
          <!-- Modal content -->
          <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex items-start justify-between p-4 border-b rounded-t dark:border-gray-600">
              <h3 class="text-xl font-semibold text-gray-900 dark:text-white">Trailer</h3>
              <button type="button" @click="toggleModal"
                class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white z"
                aria-label="Close modal">
                <svg aria-hidden="true" class="w-8 h-8" fill="currentColor" viewBox="0 0 20 20"
                  xmlns="http://www.w3.org/2000/svg">
                  <path fill-rule="evenodd"
                    d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                    clip-rule="evenodd"></path>
                </svg>
              </button>
            </div>
            <!-- Modal body -->
            <div class="p-6 space-y-6">
              <div class="aspect-w-16 aspect-h-9">
                <iframe class="absolute inset-0 w-full h-full"
                  :src="url" allowfullscreen></iframe>
              </div>
            </div>
            <!-- Modal footer -->
            <div class="flex items-center justify-end p-4 border-t dark:border-gray-600">
              <button type="button" @click="toggleModal"
                class="px-4 py-2 text-sm font-medium text-gray-900 dark:text-white bg-gray-200 dark:bg-gray-800 rounded-lg hover:bg-gray-300 dark:hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-400">
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Backdrop filter -->
      <div v-show="isModalOpen" class="fixed inset-0 bg-black bg-opacity-50 z-40"></div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      url: {
        type: String,
        default: 'https://www.youtube.com/embed/6p-lDYPR2P8',
      },
    },
    data() {
      return {
        isModalOpen: false,
      };
    },
    methods: {
      toggleModal() {
        this.isModalOpen = !this.isModalOpen;
        if (!this.isModalOpen) {
          this.pauseVideo();
        }
      },
      pauseVideo() {
        const iframes = document.querySelectorAll('iframe');
        iframes.forEach((iframe) => {
          const source = iframe.src;
          iframe.src = '';
          iframe.src = source;
        });
      },
    },
  };
  </script>
  
  <style>
  .ctn {
    margin-left: 0;
  }
  
  /* Apply backdrop filter to the surrounding elements */
  .fixed {
  backdrop-filter: blur(8px);
}
  /* Center the modal */
.fixed.inset-0 {
  display: flex;
  align-items: center;
  justify-content: center;
}

/* Hide scrollbar when modal is open */
body.modal-open {
  overflow: hidden;
}

/* Adjust the size of the modal container */
.w-full.max-w-6xl {
  max-width: 650px; /* Adjust the desired width */
}

/* Adjust the size of the iframe container */
.aspect-w-16 {
  width: 100%; /* Adjust the desired width */
}

.aspect-h-9 {
  height: 0;
  padding-bottom: 56.25%; /* Adjust the desired aspect ratio (9:16 = 56.25%) */
}
.z{
    z-index: 10;
}
  </style>
  