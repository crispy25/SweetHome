import React, { useState } from "react";

type Props = {
  onChange?: (urls: string[]) => void;
};

const CLOUD_URL = import.meta.env.VITE_CLOUDINARY_URL;

export default function ImageUploader({ onChange }: Props) {
  const [images, setImages] = useState<string[]>([]);
  const [uploading, setUploading] = useState<boolean>(false);

  const uploadToCloudinary = async (file: File): Promise<string> => {
    const formData = new FormData();
    formData.append("file", file);
    formData.append("upload_preset", import.meta.env.VITE_CLOUDINARY_UPLOAD_PRESET);

    console.log(CLOUD_URL);

    const res = await fetch(
    CLOUD_URL,
      {
        method: "POST",
        body: formData,
      }
    );

    const data = await res.json();

    if (!res.ok) {
      throw new Error(data?.error?.message || "Upload failed");
    }

    return data.secure_url as string;
  };

  const handleUpload = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const files = e.target.files ? Array.from(e.target.files) : [];
    if (!files.length) return;

    setUploading(true);

    try {
      const uploadedUrls = await Promise.all(
        files.map((file) => uploadToCloudinary(file))
      );

      const newImages = [...images, ...uploadedUrls];
      setImages(newImages);

      onChange?.(newImages);
    } catch (err) {
      console.error(err);
    } finally {
      setUploading(false);
      e.target.value = "";
    }
  };

  const handleDelete = (index: number) => {
    const updated = images.filter((_, i) => i !== index);
    setImages(updated);
    onChange?.(updated);
  };

  return (
    <div style={{ maxWidth: 600 }}>
      <input type="file" multiple accept="image/*" onChange={handleUpload} />

      {uploading && <p>Uploading...</p>}

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(3, 1fr)",
          gap: 10,
          marginTop: 20,
        }}
      >
        {images.map((url, index) => (
          <div key={url + index} style={{ position: "relative" }}>
            <img
              src={url}
              alt="uploaded"
              style={{
                width: "100%",
                height: 120,
                objectFit: "cover",
                borderRadius: 8,
              }}
            />

            <button
              onClick={() => handleDelete(index)}
              style={{
                position: "absolute",
                top: 6,
                right: 6,
                background: "red",
                color: "white",
                border: "none",
                borderRadius: 4,
                cursor: "pointer",
              }}
            >
              ✕
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}