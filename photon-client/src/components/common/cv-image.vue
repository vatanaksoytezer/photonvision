<template>
  <img
    id="CameraStream"
    :style="styleObject"
    :src="address"
    crossorigin="Anonymous"
    alt=""
    @click="e => $emit('click', e)"
  >
</template>

<script>
    export default {
        name: "CvImage",
      // eslint-disable-next-line vue/require-prop-types
        props: ['address', 'scale'],
        data: () => {
            return {}
        },
        computed: {
            styleObject: {
                get() {
                    return {
                        width: `${this.scale}%`,
                        height: `${this.scale}%`,
                        display: 'block',
                        margin: 'auto'
                    }
                }

            }
        }
    }
</script>