export interface MovieDto {
    id?: number;
    title?: string;
    director?: string;
    yearOfPublication?: string;
    description?: string;
    userUploadedIt?: string;
    likes?: number;
    dislikes?: number;
    createdAt?: Date;
}