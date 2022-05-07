package com.hung.wallup.data.remote.model

import com.hung.wallup.domain.model.Photo

data class Result(
    val cover_photo: CoverPhoto?,
    val curated: Boolean?, // false
    val description: Any?, // null
    val featured: Boolean?, // false
    val id: String?, // 2102317
    val last_collected_at: String?, // 2022-05-01T10:18:18-04:00
    val links: Links?,
    val preview_photos: List<PreviewPhoto?>?,
    val `private`: Boolean?, // false
    val published_at: String?, // 2018-05-12T03:22:56-04:00
    val share_key: String?, // ff21f4fbbd5935b231e252b61dc54226
    val tags: List<Tag?>?,
    val title: String?, // car
    val total_photos: Int?, // 495
    val updated_at: String?, // 2022-05-01T10:18:18-04:00
    val user: User?
) {
    data class CoverPhoto(
        val alt_description: Any?, // null
        val blur_hash: String?, // L_L4sbRjoLof~qWBj[fQWYofWBWB
        val categories: List<Any?>?,
        val color: String?, // #f3f3f3
        val created_at: String?, // 2022-04-25T15:15:27-04:00
        val current_user_collections: List<Any?>?,
        val description: Any?, // null
        val height: Int?, // 4160
        val id: String?, // Hcu90mJmo9w
        val liked_by_user: Boolean?, // false
        val likes: Int?, // 2
        val links: Links?,
        val promoted_at: Any?, // null
        val sponsorship: Any?, // null
        val topic_submissions: TopicSubmissions?,
        val updated_at: String?, // 2022-05-04T18:26:15-04:00
        val urls: Urls?,
        val user: User?,
        val width: Int? // 6240
    ) {
        data class Links(
            val download: String?, // https://unsplash.com/photos/Hcu90mJmo9w/download
            val download_location: String?, // https://api.unsplash.com/photos/Hcu90mJmo9w/download
            val html: String?, // https://unsplash.com/photos/Hcu90mJmo9w
            val self: String? // https://api.unsplash.com/photos/Hcu90mJmo9w
        )

        data class TopicSubmissions(
            val `3d-renders`: DRenders?,
            val technology: Technology?,
            val wallpapers: Wallpapers?
        ) {
            data class DRenders(
                val approved_on: String?, // 2021-10-02T04:00:51-04:00
                val status: String? // approved
            )

            data class Technology(
                val approved_on: String?, // 2021-08-05T04:20:04-04:00
                val status: String? // approved
            )

            data class Wallpapers(
                val approved_on: String?, // 2022-04-13T07:59:48-04:00
                val status: String? // rejected
            )
        }

        data class Urls(
            val full: String?, // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb
            val raw: String?, // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1
            val regular: String?, // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max
            val small: String?, // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max
            val small_s3: String?, // https://s3.us-west-2.amazonaws.com/images.unsplash.com/photo-1650913542178-a74471fe4733
            val thumb: String? // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max
        )

        data class User(
            val accepted_tos: Boolean?, // true
            val bio: String?, // that's my life.
            val first_name: String?, // Ömer Haktan
            val for_hire: Boolean?, // true
            val id: String?, // e2R9DLlx2w4
            val instagram_username: String?, // omerhaktanbulut
            val last_name: String?, // Bulut
            val links: Links?,
            val location: String?, // Turkey
            val name: String?, // Ömer Haktan Bulut
            val portfolio_url: String?, // https://omerhaktan.com/
            val profile_image: ProfileImage?,
            val social: Social?,
            val total_collections: Int?, // 1
            val total_likes: Int?, // 23
            val total_photos: Int?, // 181
            val twitter_username: String?, // OmerHAKTANN
            val updated_at: String?, // 2022-05-04T19:36:01-04:00
            val username: String? // omerhaktan
        ) {
            data class Links(
                val followers: String?, // https://api.unsplash.com/users/omerhaktan/followers
                val following: String?, // https://api.unsplash.com/users/omerhaktan/following
                val html: String?, // https://unsplash.com/@omerhaktan
                val likes: String?, // https://api.unsplash.com/users/omerhaktan/likes
                val photos: String?, // https://api.unsplash.com/users/omerhaktan/photos
                val portfolio: String?, // https://api.unsplash.com/users/omerhaktan/portfolio
                val self: String? // https://api.unsplash.com/users/omerhaktan
            )

            data class ProfileImage(
                val large: String?, // https://images.unsplash.com/profile-1633041263038-233e45cbfe85image?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128
                val medium: String?, // https://images.unsplash.com/profile-1633041263038-233e45cbfe85image?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64
                val small: String? // https://images.unsplash.com/profile-1633041263038-233e45cbfe85image?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32
            )

            data class Social(
                val instagram_username: String?, // omerhaktanbulut
                val paypal_email: Any?, // null
                val portfolio_url: String?, // https://omerhaktan.com/
                val twitter_username: String? // OmerHAKTANN
            )
        }
    }

    data class Links(
        val html: String?, // https://unsplash.com/collections/2102317/car
        val photos: String?, // https://api.unsplash.com/collections/2102317/photos
        val related: String?, // https://api.unsplash.com/collections/2102317/related
        val self: String? // https://api.unsplash.com/collections/2102317
    )

    data class PreviewPhoto(
        val blur_hash: String?, // L_L4sbRjoLof~qWBj[fQWYofWBWB
        val created_at: String?, // 2022-04-25T15:15:27-04:00
        val id: String?, // Hcu90mJmo9w
        val updated_at: String?, // 2022-05-04T18:26:15-04:00
        val urls: Urls?
    ) {
        data class Urls(
            val full: String?, // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb
            val raw: String?, // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1
            val regular: String?, // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max
            val small: String?, // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max
            val small_s3: String?, // https://s3.us-west-2.amazonaws.com/images.unsplash.com/photo-1650913542178-a74471fe4733
            val thumb: String? // https://images.unsplash.com/photo-1650913542178-a74471fe4733?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max
        )
    }

    data class Tag(
        val source: Source?,
        val title: String?, // car
        val type: String? // landing_page
    ) {
        data class Source(
            val ancestry: Ancestry?,
            val cover_photo: CoverPhoto?,
            val description: String?, // Choose from a curated selection of car photos. Always free on Unsplash.
            val meta_description: String?, // Choose from hundreds of free car pictures. Download HD car photos for free on Unsplash.
            val meta_title: String?, // Best 500+ Car Photos [Spectacular] | Download Car Images & Pictures - Unsplash
            val subtitle: String?, // Download free car images
            val title: String? // Car images & pictures
        ) {
            data class Ancestry(
                val category: Category?,
                val subcategory: Subcategory?,
                val type: Type?
            ) {
                data class Category(
                    val pretty_slug: String?, // Things
                    val slug: String? // things
                )

                data class Subcategory(
                    val pretty_slug: String?, // Car
                    val slug: String? // car
                )

                data class Type(
                    val pretty_slug: String?, // Images
                    val slug: String? // images
                )
            }

            data class CoverPhoto(
                val alt_description: String?, // white car
                val blur_hash: String?, // L95Y4=I9Mw%NWAj?j]a}8^%hxvIA
                val categories: List<Any?>?,
                val color: String?, // #262640
                val created_at: String?, // 2017-04-14T00:59:12-04:00
                val current_user_collections: List<Any?>?,
                val description: String?, // I shot this while doing a job for a luxury automotive storage facility in Baltimore, MD. I wanted to create an ominous sense of intrigue, giving the feeling of a space that was both expansive and enclosed. I enjoy the journey my eyes take from the focal point of the headlamps to the contours of the Camero’s body, and then to the backdrop of stacked automobiles.
                val height: Int?, // 3164
                val id: String?, // m3m-lnR90uM
                val liked_by_user: Boolean?, // false
                val likes: Int?, // 1883
                val links: Links?,
                val promoted_at: String?, // 2017-04-14T13:20:15-04:00
                val sponsorship: Any?, // null
                val topic_submissions: TopicSubmissions?,
                val updated_at: String?, // 2022-04-30T09:01:23-04:00
                val urls: Urls?,
                val user: User?,
                val width: Int? // 5357
            ) {
                data class Links(
                    val download: String?, // https://unsplash.com/photos/m3m-lnR90uM/download
                    val download_location: String?, // https://api.unsplash.com/photos/m3m-lnR90uM/download
                    val html: String?, // https://unsplash.com/photos/m3m-lnR90uM
                    val self: String? // https://api.unsplash.com/photos/m3m-lnR90uM
                )

                data class TopicSubmissions(
                    val arts_culture: ArtsCulture?,
                    val current_events: CurrentEvents?,
                    val spirituality: Spirituality?,
                    val textures_patterns: TexturesPatterns?
                ) {
                    data class ArtsCulture(
                        val approved_on: String?, // 2020-04-06T10:20:25-04:00
                        val status: String? // approved
                    )

                    data class CurrentEvents(
                        val approved_on: String?, // 2021-03-01T07:52:57-05:00
                        val status: String? // approved
                    )

                    data class Spirituality(
                        val approved_on: String?, // 2020-04-06T10:20:22-04:00
                        val status: String? // approved
                    )

                    data class TexturesPatterns(
                        val approved_on: String?, // 2020-04-06T10:20:11-04:00
                        val status: String? // approved
                    )
                }

                data class Urls(
                    val full: String?, // https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb
                    val raw: String?, // https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?ixlib=rb-1.2.1
                    val regular: String?, // https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max
                    val small: String?, // https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max
                    val small_s3: String?, // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1492144534655-ae79c964c9d7
                    val thumb: String? // https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max
                )

                data class User(
                    val accepted_tos: Boolean?, // true
                    val bio: Any?, // null
                    val first_name: String?, // Peter
                    val for_hire: Boolean?, // false
                    val id: String?, // 9aTMQdp_Djo
                    val instagram_username: String?, // pnbroom
                    val last_name: String?, // Broomfield
                    val links: Links?,
                    val location: String?, // Baltimore, MD
                    val name: String?, // Peter Broomfield
                    val portfolio_url: String?, // http://workingdesignstudio.com/
                    val profile_image: ProfileImage?,
                    val social: Social?,
                    val total_collections: Int?, // 36
                    val total_likes: Int?, // 116
                    val total_photos: Int?, // 1
                    val twitter_username: Any?, // null
                    val updated_at: String?, // 2022-04-30T02:46:13-04:00
                    val username: String? // peterbroomfield
                ) {
                    data class Links(
                        val followers: String?, // https://api.unsplash.com/users/peterbroomfield/followers
                        val following: String?, // https://api.unsplash.com/users/peterbroomfield/following
                        val html: String?, // https://unsplash.com/@peterbroomfield
                        val likes: String?, // https://api.unsplash.com/users/peterbroomfield/likes
                        val photos: String?, // https://api.unsplash.com/users/peterbroomfield/photos
                        val portfolio: String?, // https://api.unsplash.com/users/peterbroomfield/portfolio
                        val self: String? // https://api.unsplash.com/users/peterbroomfield
                    )

                    data class ProfileImage(
                        val large: String?, // https://images.unsplash.com/profile-1651250388596-275f952916a2image?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128
                        val medium: String?, // https://images.unsplash.com/profile-1651250388596-275f952916a2image?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64
                        val small: String? // https://images.unsplash.com/profile-1651250388596-275f952916a2image?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32
                    )

                    data class Social(
                        val instagram_username: String?, // pnbroom
                        val paypal_email: Any?, // null
                        val portfolio_url: String?, // http://workingdesignstudio.com/
                        val twitter_username: Any? // null
                    )
                }
            }
        }
    }

    data class User(
        val accepted_tos: Boolean?, // false
        val bio: Any?, // null
        val first_name: String?, // Om
        val for_hire: Boolean?, // false
        val id: String?, // eBhwcJ2doec
        val instagram_username: Any?, // null
        val last_name: String?, // K
        val links: Links?,
        val location: Any?, // null
        val name: String?, // Om K
        val portfolio_url: Any?, // null
        val profile_image: ProfileImage?,
        val social: Social?,
        val total_collections: Int?, // 61
        val total_likes: Int?, // 0
        val total_photos: Int?, // 0
        val twitter_username: Any?, // null
        val updated_at: String?, // 2022-05-01T10:12:03-04:00
        val username: String? // omz_412
    ) {
        data class Links(
            val followers: String?, // https://api.unsplash.com/users/omz_412/followers
            val following: String?, // https://api.unsplash.com/users/omz_412/following
            val html: String?, // https://unsplash.com/@omz_412
            val likes: String?, // https://api.unsplash.com/users/omz_412/likes
            val photos: String?, // https://api.unsplash.com/users/omz_412/photos
            val portfolio: String?, // https://api.unsplash.com/users/omz_412/portfolio
            val self: String? // https://api.unsplash.com/users/omz_412
        )

        data class ProfileImage(
            val large: String?, // https://images.unsplash.com/profile-1572266989535-484519e4777cimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128
            val medium: String?, // https://images.unsplash.com/profile-1572266989535-484519e4777cimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64
            val small: String? // https://images.unsplash.com/profile-1572266989535-484519e4777cimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32
        )

        data class Social(
            val instagram_username: Any?, // null
            val paypal_email: Any?, // null
            val portfolio_url: Any?, // null
            val twitter_username: Any? // null
        )
    }
}


fun Result.toDomainPhoto(): Photo {
    return Photo(
        id = this.cover_photo?.id.toString(),
        coverPhoto = this.cover_photo!!.urls!!.regular.toString()
    )
}